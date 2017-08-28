/*
 * Copyright (c) 2016, 2017, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

/*
 * @test
 * @requires vm.aot
 * @library /test/lib /testlibrary /
 * @modules java.base/jdk.internal.misc
 * @requires vm.bits == "64" & (os.arch == "amd64" | os.arch == "x86_64")
 * @run driver compiler.aot.cli.NonExistingAOTLibraryTest
 * @summary check if non-existing aot library is handled properly
 */

package compiler.aot.cli;

import java.io.File;

public class NonExistingAOTLibraryTest {
    private static final String PATH = "./NonExisting.so";
    private static final String OPTION = "-XX:AOTLibrary=" + PATH;
    private static final String[] EXPECTED_MESSAGES = new String[] {
        "error opening file"
    };

    public static void main(String args[]) {
        if (new File(PATH).exists()) {
            throw new Error("TESTBUG: " + PATH + " unexpectedly exists");
        }
        AotLibraryNegativeBase.launchTest(OPTION, EXPECTED_MESSAGES);
    }
}
