package com.nanlagger.blacksails

import com.badlogic.gdx.ApplicationAdapter

/**
 * Created by NaNLagger on 31.03.15.
 * @author Stepan Lyashenko
 */
class MyGdxGame extends ApplicationAdapter {

  override def create() {
    GameMain.create()
  }

  override def render() {
    GameMain.render()
  }
}
