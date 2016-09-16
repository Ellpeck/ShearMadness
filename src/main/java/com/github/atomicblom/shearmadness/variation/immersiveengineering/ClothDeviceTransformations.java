package com.github.atomicblom.shearmadness.variation.immersiveengineering;

import com.github.atomicblom.shearmadness.api.rendering.PartDefinition;
import com.github.atomicblom.shearmadness.api.rendering.QuadrupedTransformDefinition;
import org.lwjgl.util.vector.Matrix3f;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import java.util.Optional;

/**
 * Created by codew on 16/09/2016.
 */
public class ClothDeviceTransformations extends QuadrupedTransformDefinition {
    @Override
    public void defineParts() {
        final Matrix4f rotate = new Matrix4f().rotate((float) Math.toRadians(-90), new Vector3f(1, 0, 0));
        bodyPartDefinition = Optional.of(new PartDefinition(
                new Vector3f(0.0f, 5.0f, 2.0f),
                Matrix4f.mul(
                        createPartMatrix(
                                new Vector3f(12, 20, 10),
                                new Vector3f(0, -1, -6)), rotate, null),
                new Matrix3f()
        ));
    }
}
