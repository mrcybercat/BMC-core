package multi.converter;

import multi.converter.algorithm.AlgorithmType;
import org.apache.commons.cli.*;
import org.apache.commons.cli.Options;
;

public class MainDriver {
    public static void main(String[] args) {
        Options options = getOptions();

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        Workflow workflow = null;

        try {
            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption("h")) {
                usage(options);
                return;
            }

            String[] remainingArgs = cmd.getArgs();
            if (remainingArgs.length < 1) {
                usage(options);
                return;
            }

            String command = remainingArgs[0];

            switch (command) {
                case "compress":
                    workflow = new Workflow(
                            new AlgorithmOptions(
                                cmd.getOptionValue("i"),
                                cmd.getOptionValue("o"),
                                2,
                                AlgorithmType.STANDARD_JPEG,
                                false
                            )
                    );
                    workflow.runWorkflow();
                    break;
                case "metric":
                    if (!cmd.hasOption("m")) {
                        usage(options);
                        return;
                    }
                    switch (cmd.getOptionValue("m")){
                        case "image":
                            workflow = new Workflow(
                                    new AlgorithmOptions(
                                            cmd.getOptionValue("i"),
                                            cmd.getOptionValue("o"),
                                            2,
                                            AlgorithmType.IMAGE_METRIC,
                                            false
                                    )
                            );
                            workflow.runWorkflow();
                            break;
                        case "video-sequential":
                            workflow = new Workflow(
                                    new AlgorithmOptions(
                                            cmd.getOptionValue("i"),
                                            cmd.getOptionValue("o"),
                                            2,
                                            AlgorithmType.VIDEO_METRIC,
                                            false
                                    )
                            );
                            workflow.runWorkflow();
                            break;
                        case "video-spacial":
                            workflow = new Workflow(
                                    new AlgorithmOptions(
                                            cmd.getOptionValue("i"),
                                            cmd.getOptionValue("o"),
                                            2,
                                            AlgorithmType.VIDEO_METRIC3D,
                                            false
                                    )
                            );
                            workflow.runWorkflow();
                            break;
                    }
                    break;

                default:
                    usage(options);
                    return;
            }

        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("java -jar bmc.jar <command> [options]", options);
            System.exit(1);
        }
    }

    private static Options getOptions() {
        Options options = new Options();

        Option help = new Option("i", "input", false, "Usage information");
        help.setRequired(false);
        options.addOption(help);

        Option input = new Option("i", "input", true, "Input file path");
        input.setRequired(true);
        options.addOption(input);

        Option output = new Option("o", "output", true, "Output file path");
        output.setRequired(true);
        options.addOption(output);

        Option preset = new Option("p", "preset", true, "Preset for metrics");
        preset.setRequired(false);
        options.addOption(preset);

        Option metricType = new Option("m", "metric-type", true, "Type of metric to be used");
        metricType.setRequired(false);
        options.addOption(metricType);
        return options;
    }

    private static void usage(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("java -jar bmc.jar <command> [options]", options);
    }
}
