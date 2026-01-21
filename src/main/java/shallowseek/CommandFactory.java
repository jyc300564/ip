package shallowseek;

import shallowseek.exceptions.ShallowSeekException;

@FunctionalInterface
public interface CommandFactory {
    Command create(String args) throws ShallowSeekException;
}
