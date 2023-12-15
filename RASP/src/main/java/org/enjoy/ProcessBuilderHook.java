package org.enjoy;

import java.util.Arrays;
import java.util.List;
public class ProcessBuilderHook {
    public static void start(List<String> commands) {
        String[] commandArr = commands.toArray(new String[commands.size()]);
        System.out.println(Arrays.toString(commandArr));
    }
}
