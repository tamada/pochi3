package jp.cafebabe.pochi.cli;

import picocli.CommandLine.IVersionProvider;

public class VersionProvider implements IVersionProvider {
    @Override
    public String[] getVersion() {
        return new String[] {
            "${VERSION}",
        };
    }
}
