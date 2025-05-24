package com.lightricks;

import com.lightricks.utils.Config;
import com.lightricks.utils.ConfigLoader;
import com.lightricks.utils.ImageIOUtil;
import com.lightricks.utils.OperationRegistry;

import java.awt.image.BufferedImage;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws Exception {
        // Check if the input is correct: we expect --config <file>
        if (args.length != 2 || !args[0].equals("--config")) {
            System.out.println("Usage: edit-image --config path_to_config.json");
            return;
        }

        // Load the config from the JSON file
        Config config = ConfigLoader.load(args[1]);

        // Make sure we have either 'output' or 'display' set in config
        if (config.output == null && !config.display) {
            throw new IllegalArgumentException("The config file must contain either 'output' or 'display': true");
        }

        // Load the input image
        BufferedImage image = ImageIOUtil.load(config.input);

        // Apply all the operations from the config file
        for (Map<String, Object> operation : config.operations) {
            String operationType = (String) operation.get("type");
            image = OperationRegistry.apply(operationType, image, operation);
        }

        // Show or save the final image
        if (config.display && config.output == null) {
            // Show image in a window
            final BufferedImage imageToDisplay = image;
            javax.swing.SwingUtilities.invokeLater(() -> {
                var frame = new javax.swing.JFrame("Image Preview");
                frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
                frame.add(new javax.swing.JLabel(new javax.swing.ImageIcon(imageToDisplay)));
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            });
        } else {
            // Save image to output file
            ImageIOUtil.save(image, config.output);
        }
    }
}