package com.lightning.northstar.block.tech.temperature_regulator;

import com.simibubi.create.foundation.networking.BlockEntityConfigurationPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;

public class TemperatureRegulatorEditPacket extends BlockEntityConfigurationPacket<TemperatureRegulatorBlockEntity> {

    private int offsetX;
    private int offsetY;
    private int offsetZ;
    private int sizeChangeX;
    private int sizeChangeY;
    private int sizeChangeZ;
    private int temp;
    private boolean envFill;

    public TemperatureRegulatorEditPacket(FriendlyByteBuf buffer) {
        super(buffer);
    }

    public TemperatureRegulatorEditPacket(BlockPos pos, int offX, int offY, int offZ, int sizeX, int sizeY, int sizeZ, int tempChange, boolean envFill) {
        super(pos);
        this.offsetX = offX;
        this.offsetY = offY;
        this.offsetZ = offZ;
        this.sizeChangeX = sizeX;
        this.sizeChangeY = sizeY;
        this.sizeChangeZ = sizeZ;
        this.temp = tempChange;
        this.envFill = envFill;
    }

    @Override
    protected void writeSettings(FriendlyByteBuf buffer) {
        buffer.writeVarInt(offsetX);
        buffer.writeVarInt(offsetY);
        buffer.writeVarInt(offsetZ);
        buffer.writeVarInt(sizeChangeX);
        buffer.writeVarInt(sizeChangeY);
        buffer.writeVarInt(sizeChangeZ);
        buffer.writeVarInt(temp);
        buffer.writeBoolean(envFill);
    }

    @Override
    protected void readSettings(FriendlyByteBuf buffer) {
        offsetX = buffer.readVarInt();
        offsetY = buffer.readVarInt();
        offsetZ = buffer.readVarInt();
        sizeChangeX = buffer.readVarInt();
        sizeChangeY = buffer.readVarInt();
        sizeChangeZ = buffer.readVarInt();
        temp = buffer.readVarInt();
        envFill = buffer.readBoolean();
    }

    @Override
    protected void applySettings(TemperatureRegulatorBlockEntity be) {
        be.changeTemp(temp);
        be.changeSize(sizeChangeX, sizeChangeY, sizeChangeZ, offsetX, offsetY, offsetZ, envFill);
    }

}