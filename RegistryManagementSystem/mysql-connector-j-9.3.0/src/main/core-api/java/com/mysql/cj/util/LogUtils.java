/*
 * Copyright (c) 2005, 2025, Oracle and/or its affiliates.
 *
 * This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License, version 2.0, as published by
 * the Free Software Foundation.
 *
 * This program is designed to work with certain software that is licensed under separate terms, as designated in a particular file or component or in
 * included license documentation. The authors of MySQL hereby grant you an additional permission to link the program and your derivative works with the
 * separately licensed software that they have either included with the program or referenced in the documentation.
 *
 * Without limiting anything contained in the foregoing, this file, which is part of MySQL Connector/J, is also subject to the Universal FOSS Exception,
 * version 1.0, a copy of which can be found at http://oss.oracle.com/licenses/universal-foss-exception.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License, version 2.0, for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA
 */

package com.mysql.cj.util;

import com.mysql.cj.conf.PropertyDefinitions;

public class LogUtils {

    public static final String CALLER_INFORMATION_NOT_AVAILABLE = "Caller information not available";

    private static final String LINE_SEPARATOR = System.getProperty(PropertyDefinitions.SYSP_line_separator);

    private static final int LINE_SEPARATOR_LENGTH = LINE_SEPARATOR.length();

    public static String findCallingClassAndMethod(Throwable t) {
        String stackTraceAsString = Util.stackTraceToString(t);

        String callingClassAndMethod = CALLER_INFORMATION_NOT_AVAILABLE;

        int endInternalMethods = Math.max(Math.max(stackTraceAsString.lastIndexOf("com.mysql.cj"), stackTraceAsString.lastIndexOf("com.mysql.cj.core")),
                stackTraceAsString.lastIndexOf("com.mysql.cj.jdbc"));

        if (endInternalMethods != -1) {
            int endOfLine = stackTraceAsString.indexOf(LINE_SEPARATOR, endInternalMethods);

            if (endOfLine != -1) {
                int nextEndOfLine = stackTraceAsString.indexOf(LINE_SEPARATOR, endOfLine + LINE_SEPARATOR_LENGTH);
                callingClassAndMethod = nextEndOfLine != -1 ? stackTraceAsString.substring(endOfLine + LINE_SEPARATOR_LENGTH, nextEndOfLine)
                        : stackTraceAsString.substring(endOfLine + LINE_SEPARATOR_LENGTH);
            }
        }

        if (!callingClassAndMethod.startsWith("\tat ") && !callingClassAndMethod.startsWith("at ")) {
            return "at " + callingClassAndMethod;
        }

        return callingClassAndMethod;
    }

}
