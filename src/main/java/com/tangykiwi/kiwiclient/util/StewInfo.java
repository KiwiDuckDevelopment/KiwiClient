package com.tangykiwi.kiwiclient.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ChatUtil;
import net.minecraft.util.Formatting;

import java.util.List;
public class StewInfo {

    static MinecraftClient minecraft = MinecraftClient.getInstance();
    public static void onInjectTooltip(Object stackIn, List<Text> list) {
        ItemStack stack = (ItemStack) stackIn;
        if (stack != null && (stack.getItem() == Items.SUSPICIOUS_STEW)){
            CompoundTag tag = stack.getTag();
            if (tag != null) {
                ListTag effects = tag.getList("Effects", 10);
                int effectsCount = effects.size();

                for (int i = 0; i < effectsCount; i++) {
                    tag = effects.getCompound(i);
                    int duration = tag.getInt("EffectDuration");
                    StatusEffect effect = StatusEffect.byRawId(tag.getByte("EffectId"));
                    list.add(new TranslatableText(effect.getTranslationKey())
                            .append(" "+ChatUtil.ticksToString(duration))
                            .setStyle(Style.EMPTY.withColor(Formatting.GRAY)));
                }
            }
        }
    }
}