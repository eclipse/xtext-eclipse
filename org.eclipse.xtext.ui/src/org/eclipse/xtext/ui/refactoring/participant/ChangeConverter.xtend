package org.eclipse.xtext.ui.refactoring.participant

import com.google.inject.Inject
import org.eclipse.core.resources.IWorkspace
import org.eclipse.core.runtime.Path
import org.eclipse.emf.common.util.URI
import org.eclipse.ltk.core.refactoring.Change
import org.eclipse.ltk.core.refactoring.CompositeChange
import org.eclipse.ltk.core.refactoring.TextFileChange
import org.eclipse.ltk.core.refactoring.resource.MoveResourceChange
import org.eclipse.text.edits.MultiTextEdit
import org.eclipse.text.edits.ReplaceEdit
import org.eclipse.xtext.ide.serializer.IEmfResourceChange
import org.eclipse.xtext.ide.serializer.ITextDocumentChange
import org.eclipse.xtext.ui.refactoring.impl.StatusWrapper
import org.eclipse.xtext.util.IAcceptor
import com.google.common.base.Predicate

class ChangeConverter implements IAcceptor<IEmfResourceChange> {
	
	CompositeChange currentChange 
	StatusWrapper status
	Predicate<Change> changeFilter
	
	@Inject(optional=true) IWorkspace workspace

	def initialize(String name, StatusWrapper status, Predicate<Change> changeFilter) {
		currentChange = new CompositeChange(name)
		this.status = status
		this.changeFilter = changeFilter
	}
	
	override accept(IEmfResourceChange emfResourceChange) {
		doConvert(emfResourceChange)
	}
	
	def Change getChange() {
		if (currentChange.children.length === 0)
			return null
		else
			return currentChange
	}

	protected def dispatch void doConvert(IEmfResourceChange change) {
		// TODO: content changes
		handleUriChange(change)
	}

	protected def dispatch void doConvert(ITextDocumentChange change) {
		handleReplacements(change)
		handleUriChange(change)
	}
	
	protected def void handleReplacements(ITextDocumentChange change) {
		if(change.replacements.size > 0) {
			val textEdits = change.replacements.map[ replacement |
				new ReplaceEdit(replacement.offset, replacement.length, replacement.replacementText)
			]
			val file = change.newURI.toFile
			val textEdit = new MultiTextEdit()
			textEdit.addChildren(textEdits)
			val textFileChange = new TextFileChange(change.oldURI.lastSegment, file)
			textFileChange.setSaveMode(TextFileChange.FORCE_SAVE)
			textFileChange.setEdit(textEdit)
			textFileChange.setTextType(change.oldURI.fileExtension)
			addChange(textFileChange)
		}
	}
	
	protected def void handleUriChange(IEmfResourceChange change) {
		if(change.newURI != change.oldURI) {
			if(change.newURI.lastSegment == change.oldURI.lastSegment) { 
				val newFile = change.newURI.toFile
				val newContainer = newFile.parent
				val oldFile = change.oldURI.toFile
				val ltkChange = new MoveResourceChange(oldFile, newContainer)
				addChange(ltkChange)
			} 
		}
	}
	
	protected def void addChange(Change change) {
		if(changeFilter.apply(change))
			currentChange.add(change)
	}
	
	protected def toFile(URI uri) {
		workspace.root.getFile(new Path(uri.toPlatformString(true)))
	}
}