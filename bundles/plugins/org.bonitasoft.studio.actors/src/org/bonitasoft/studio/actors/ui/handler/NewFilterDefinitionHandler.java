/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.actors.ui.handler;

import org.bonitasoft.studio.actors.ActorsPlugin;
import org.bonitasoft.studio.actors.repository.ActorFilterDefRepositoryStore;
import org.bonitasoft.studio.actors.ui.wizard.FilterDefinitionWizard;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.connector.model.definition.dialog.ConnectorDefinitionWizardDialog;
import org.bonitasoft.studio.connector.model.i18n.DefinitionResourceProvider;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;

/**
 * @author Romain Bioteau
 *
 */
public class NewFilterDefinitionHandler extends AbstractHandler {

    /* (non-Javadoc)
     * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
     */
    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        final ActorFilterDefRepositoryStore  defStore = (ActorFilterDefRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ActorFilterDefRepositoryStore.class) ;
        final DefinitionResourceProvider messageProvider = DefinitionResourceProvider.getInstance(defStore,ActorsPlugin.getDefault().getBundle());
        FilterDefinitionWizard wizard = new FilterDefinitionWizard(messageProvider) ;

        WizardDialog wd = new ConnectorDefinitionWizardDialog(Display.getCurrent().getActiveShell(),wizard,messageProvider) ;
        wd.open();
        return null ;
    }

}
