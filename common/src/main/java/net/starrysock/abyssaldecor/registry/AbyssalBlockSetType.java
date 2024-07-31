package net.starrysock.abyssaldecor.registry;

import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;

import java.util.Set;
import java.util.stream.Stream;

public record AbyssalBlockSetType(String name, boolean canOpenByHand, SoundType soundType, SoundEvent doorClose, SoundEvent doorOpen, SoundEvent trapdoorClose, SoundEvent trapdoorOpen, SoundEvent pressurePlateClickOff, SoundEvent pressurePlateClickOn, SoundEvent buttonClickOff, SoundEvent buttonClickOn) {
    private static final Set<AbyssalBlockSetType> VALUES = new ObjectArraySet();
    public static final AbyssalBlockSetType SEABRASS;
    public static final AbyssalBlockSetType DEEPBRONZE;
    public static final AbyssalBlockSetType PEARL;
    public static final AbyssalBlockSetType BLACK_PEARL;
    public static final AbyssalBlockSetType WHITEWOOD;
    public static final AbyssalBlockSetType BLACKWOOD;
    public static final AbyssalBlockSetType CINNAMON;

    public AbyssalBlockSetType(String string) {
        this(string, true, SoundType.WOOD, SoundEvents.WOODEN_DOOR_CLOSE, SoundEvents.WOODEN_DOOR_OPEN, SoundEvents.WOODEN_TRAPDOOR_CLOSE, SoundEvents.WOODEN_TRAPDOOR_OPEN, SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_OFF, SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_ON, SoundEvents.WOODEN_BUTTON_CLICK_OFF, SoundEvents.WOODEN_BUTTON_CLICK_ON);
    }

    public AbyssalBlockSetType(String name, boolean canOpenByHand, SoundType soundType, SoundEvent doorClose, SoundEvent doorOpen, SoundEvent trapdoorClose, SoundEvent trapdoorOpen, SoundEvent pressurePlateClickOff, SoundEvent pressurePlateClickOn, SoundEvent buttonClickOff, SoundEvent buttonClickOn) {
        this.name = name;
        this.canOpenByHand = canOpenByHand;
        this.soundType = soundType;
        this.doorClose = doorClose;
        this.doorOpen = doorOpen;
        this.trapdoorClose = trapdoorClose;
        this.trapdoorOpen = trapdoorOpen;
        this.pressurePlateClickOff = pressurePlateClickOff;
        this.pressurePlateClickOn = pressurePlateClickOn;
        this.buttonClickOff = buttonClickOff;
        this.buttonClickOn = buttonClickOn;
    }

    public BlockSetType toBlockSetType() {
        return new BlockSetType(this.name, this.canOpenByHand, this.soundType, this.doorClose, this.doorOpen, this.trapdoorClose, this.trapdoorOpen, this.pressurePlateClickOff, this.pressurePlateClickOn, this.buttonClickOff, this.buttonClickOn);
    }

    private static AbyssalBlockSetType register(AbyssalBlockSetType blockSetType) {
        VALUES.add(blockSetType);
        return blockSetType;
    }

    public static Stream<AbyssalBlockSetType> values() {
        return VALUES.stream();
    }

    public static AbyssalBlockSetType byName(String name) {
        return values().filter((blockSetType) -> blockSetType.name().equals(name)).findFirst().orElse(null);
    }

    public String name() {
        return this.name;
    }

    public boolean canOpenByHand() {
        return this.canOpenByHand;
    }

    public SoundType soundType() {
        return this.soundType;
    }

    public SoundEvent doorClose() {
        return this.doorClose;
    }

    public SoundEvent doorOpen() {
        return this.doorOpen;
    }

    public SoundEvent trapdoorClose() {
        return this.trapdoorClose;
    }

    public SoundEvent trapdoorOpen() {
        return this.trapdoorOpen;
    }

    public SoundEvent pressurePlateClickOff() {
        return this.pressurePlateClickOff;
    }

    public SoundEvent pressurePlateClickOn() {
        return this.pressurePlateClickOn;
    }

    public SoundEvent buttonClickOff() {
        return this.buttonClickOff;
    }

    public SoundEvent buttonClickOn() {
        return this.buttonClickOn;
    }


    static {
        SEABRASS = register(new AbyssalBlockSetType("seabrass", true, SoundType.METAL, SoundEvents.IRON_DOOR_CLOSE, SoundEvents.IRON_DOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE, SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF, SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON, SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON));
        DEEPBRONZE = register(new AbyssalBlockSetType("deepbronze", true, SoundType.METAL, SoundEvents.IRON_DOOR_CLOSE, SoundEvents.IRON_DOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE, SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF, SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON, SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON));
        PEARL = register(new AbyssalBlockSetType("pearl", true, SoundType.STONE, SoundEvents.IRON_DOOR_CLOSE, SoundEvents.IRON_DOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE, SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.STONE_PRESSURE_PLATE_CLICK_OFF, SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON, SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON));
        BLACK_PEARL = register(new AbyssalBlockSetType("black_pearl", true, SoundType.STONE, SoundEvents.IRON_DOOR_CLOSE, SoundEvents.IRON_DOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE, SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.STONE_PRESSURE_PLATE_CLICK_OFF, SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON, SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON));
        WHITEWOOD = register(new AbyssalBlockSetType("whitewood"));
        BLACKWOOD = register(new AbyssalBlockSetType("blackwood"));
        CINNAMON = register(new AbyssalBlockSetType("cinnamon", true, SoundType.CHERRY_WOOD, SoundEvents.CHERRY_WOOD_DOOR_CLOSE, SoundEvents.CHERRY_WOOD_DOOR_OPEN, SoundEvents.CHERRY_WOOD_TRAPDOOR_CLOSE, SoundEvents.CHERRY_WOOD_TRAPDOOR_OPEN, SoundEvents.CHERRY_WOOD_PRESSURE_PLATE_CLICK_OFF, SoundEvents.CHERRY_WOOD_PRESSURE_PLATE_CLICK_ON, SoundEvents.CHERRY_WOOD_BUTTON_CLICK_OFF, SoundEvents.CHERRY_WOOD_BUTTON_CLICK_ON));
    }

}
