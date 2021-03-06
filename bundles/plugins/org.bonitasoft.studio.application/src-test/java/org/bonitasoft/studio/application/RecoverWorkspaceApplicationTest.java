/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.application;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.studio.application.advisor.RecoverWorkspaceAdvisor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * @author Romain Bioteau
 *
 */
public class RecoverWorkspaceApplicationTest {

    private RecoverWorkspaceApplication recoverWorkspaceApplication;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        recoverWorkspaceApplication = new RecoverWorkspaceApplication();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_createWorkbenchAdvisor_returns_a_RecoverWorkspaceAdvisor() throws Exception {
        assertThat(recoverWorkspaceApplication.createWorkbenchAdvisor()).isInstanceOf(RecoverWorkspaceAdvisor.class);
    }

}
