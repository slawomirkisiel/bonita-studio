/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.pagedesigner.core.repository;

import java.util.Set;

import org.bonitasoft.studio.pagedesigner.i18n.Messages;
import org.eclipse.swt.graphics.Image;

/**
 * @author Romain Bioteau
 */
public class WebWidgetRepositoryStore extends AbstractFolderRepositoryStore<WebWidgetFileStore> {

    public static final String WEB_WIDGET_REPOSITORY_NAME = "web_widgets";

    @Override
    public String getName() {
        return WEB_WIDGET_REPOSITORY_NAME;
    }

    @Override
    public String getDisplayName() {
        return Messages.widgetRepository;
    }

    @Override
    public Image getIcon() {
        return null;
    }

    @Override
    public Set<String> getCompatibleExtensions() {
        return null;
    }

    @Override
    public WebWidgetFileStore createRepositoryFileStore(final String widgetFolderName) {
        return new WebWidgetFileStore(widgetFolderName, this);
    }

}
