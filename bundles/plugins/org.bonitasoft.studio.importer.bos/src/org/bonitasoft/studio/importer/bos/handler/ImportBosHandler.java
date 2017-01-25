package org.bonitasoft.studio.importer.bos.handler;

import static com.google.common.base.Strings.isNullOrEmpty;
import static org.bonitasoft.studio.common.Messages.bonitaStudioModuleName;
import static org.bonitasoft.studio.ui.wizard.WizardPageBuilder.newPage;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.importer.bos.i18n.Messages;
import org.bonitasoft.studio.importer.bos.model.ImportArchiveModel;
import org.bonitasoft.studio.importer.bos.operation.ImportBosArchiveOperation;
import org.bonitasoft.studio.importer.bos.status.BosImportStatusDialogHandler;
import org.bonitasoft.studio.importer.bos.wizard.ImportBosArchiveControlSupplier;
import org.bonitasoft.studio.importer.ui.dialog.SkippableProgressMonitorJobsDialog;
import org.bonitasoft.studio.ui.wizard.WizardBuilder;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.widgets.Shell;

public class ImportBosHandler {

    @Execute
    public void execute(Shell activeShell, RepositoryAccessor repositoryAccessor) {
        final ImportBosArchiveControlSupplier bosArchiveControlSupplier = newImportBosArchiveControlSupplier();
        final Optional<ImportArchiveModel> archiveModel = WizardBuilder.<ImportArchiveModel> newWizard()
                .withTitle(Messages.importBosArchiveTitle)
                .havingPage(newPage()
                        .withTitle(Messages.importFileTitle)
                        .withDescription(
                                Messages.bind(Messages.importFileDescription, new Object[] { bonitaStudioModuleName }))
                        .withControl(bosArchiveControlSupplier))
                .onFinish(() -> Optional.ofNullable(bosArchiveControlSupplier.getArchiveModel()))
                .open(activeShell, Messages.importButtonLabel);

        archiveModel
                .ifPresent(model -> importArchive(activeShell, model, new File(bosArchiveControlSupplier.getFilePath()),
                        repositoryAccessor));
    }

    private void importArchive(Shell activeShell, ImportArchiveModel model, File archive,
            RepositoryAccessor repositoryAccessor) {
        final SkippableProgressMonitorJobsDialog progressManager = new SkippableProgressMonitorJobsDialog(activeShell);
        final ImportBosArchiveOperation operation = new ImportBosArchiveOperation(archive, progressManager, model);
        try {
            progressManager.run(true, false, operation);
        } catch (final InvocationTargetException | InterruptedException e) {
            final Throwable t = e instanceof InvocationTargetException
                    ? ((InvocationTargetException) e).getTargetException() : e;
            BonitaStudioLog.error("Import has failed.", e);
            String message = Messages.errorWhileImporting_message;
            if (t != null && !isNullOrEmpty(t.getMessage())) {
                message = t.getMessage();
            }
            new BonitaErrorDialog(activeShell, Messages.errorWhileImporting_title, message, e)
                    .open();
        }
        operation.openFilesToOpen();
        PlatformUtil.openIntroIfNoOtherEditorOpen();
        activeShell.getDisplay().asyncExec(() -> new BosImportStatusDialogHandler(operation.getStatus(),
                repositoryAccessor.getRepositoryStore(DiagramRepositoryStore.class))
                        .open(activeShell));
    }

    private ImportBosArchiveControlSupplier newImportBosArchiveControlSupplier() {
        return new ImportBosArchiveControlSupplier();
    }

}
