package com.amazon.device.ads;

import java.io.File;

class DefaultFileHandlerFactory implements FileHandlerFactory {
    DefaultFileHandlerFactory() {
    }

    public FileInputHandler createFileInputHandler(File file, String str) {
        FileInputHandler fileInputHandler = new FileInputHandler();
        fileInputHandler.setFile(file, str);
        return fileInputHandler;
    }

    public FileInputHandler createFileInputHandler(String str) {
        FileInputHandler fileInputHandler = new FileInputHandler();
        fileInputHandler.setFile(str);
        return fileInputHandler;
    }

    public FileInputHandler createFileInputHandler(File file) {
        FileInputHandler fileInputHandler = new FileInputHandler();
        fileInputHandler.setFile(file);
        return fileInputHandler;
    }

    public FileOutputHandler createFileOutputHandler(File file, String str) {
        FileOutputHandler fileOutputHandler = new FileOutputHandler();
        fileOutputHandler.setFile(file, str);
        return fileOutputHandler;
    }

    public FileOutputHandler createFileOutputHandler(String str) {
        FileOutputHandler fileOutputHandler = new FileOutputHandler();
        fileOutputHandler.setFile(str);
        return fileOutputHandler;
    }

    public FileOutputHandler createFileOutputHandler(File file) {
        FileOutputHandler fileOutputHandler = new FileOutputHandler();
        fileOutputHandler.setFile(file);
        return fileOutputHandler;
    }
}
