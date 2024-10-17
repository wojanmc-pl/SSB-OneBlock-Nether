package com.bgsoftware.ssboneblock.phases;

public final class IslandPhaseData {

    private final int phaseLevel;
    private final int phaseBlock;
    private int candyFactoryLevel;

    public IslandPhaseData(int phaseLevel, int phaseBlock, int candyFactoryLevel) {
        this.phaseLevel = phaseLevel;
        this.phaseBlock = phaseBlock;
        this.candyFactoryLevel = candyFactoryLevel;
    }

    public int getPhaseLevel() {
        return phaseLevel;
    }

    public int getPhaseBlock() {
        return phaseBlock;
    }

    public int getCandyFactoryLevel() {
        return candyFactoryLevel;
    }

    public void setCandyFactoryLevel(int candyFactoryLevel) {
        this.candyFactoryLevel = candyFactoryLevel;
    }

    public IslandPhaseData nextBlock() {
        return new IslandPhaseData(phaseLevel, phaseBlock + 1, candyFactoryLevel);
    }

    public IslandPhaseData nextPhase() {
        return new IslandPhaseData(phaseLevel + 1, 0, candyFactoryLevel);
    }

}
