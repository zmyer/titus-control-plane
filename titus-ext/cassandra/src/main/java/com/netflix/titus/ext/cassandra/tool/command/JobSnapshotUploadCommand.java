/*
 * Copyright 2018 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netflix.titus.ext.cassandra.tool.command;

import java.io.File;

import com.netflix.titus.ext.cassandra.tool.Command;
import com.netflix.titus.ext.cassandra.tool.CommandContext;
import com.netflix.titus.ext.cassandra.tool.snapshot.JobSnapshotLoader;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class JobSnapshotUploadCommand implements Command {

    @Override
    public String getDescription() {
        return "Loads job snapshot into Cassandra";
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.TargetKeySpace;
    }

    @Override
    public Options getOptions() {
        Options options = new Options();
        options.addOption(Option.builder("i")
                .longOpt("input folder")
                .required()
                .hasArg()
                .desc("Input folder containing snapshot files")
                .build()
        );
        return options;
    }

    @Override
    public void execute(CommandContext context) {
        File output = new File(context.getCommandLine().getOptionValue('i'));
        JobSnapshotLoader downloader = new JobSnapshotLoader(context.getTargetSession(), output);
        downloader.load();
    }
}
