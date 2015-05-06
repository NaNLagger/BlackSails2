package com.nanlagger.blacksails.utils;

import com.badlogic.gdx.math.Vector2;
import com.nanlagger.blacksails.utils.math.Position;
import junit.framework.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class Utils$Test {

    @Test
    public void testPositionToPoint() throws Exception {
        Assert.assertEquals(Utils.positionToPoint(new Position(1,1)), new Vector2(225, 114));
    }

    @Test
    public void testPointToPosition() throws Exception {
        Assert.assertEquals(Utils.pointToPosition(new Vector2(160.1601f, 173.21329f)), new Position(1, 0));
    }
}