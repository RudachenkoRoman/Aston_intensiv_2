package com.rudachenkoroman.aston_intensiv_2.util

import android.graphics.Color
import com.rudachenkoroman.aston_intensiv_2.util.Colors.ORANGE_COLOR
import com.rudachenkoroman.aston_intensiv_2.util.Colors.PURPLE_COLOR
import com.rudachenkoroman.aston_intensiv_2.util.Colors.YELLOW_COLOR

enum class SelectColor (val action: Action, val color: Int){
    RED(Action.SHOW_TEXT, Color.RED),
    ORANGE(Action.SHOW_IMAGE, Color.parseColor(ORANGE_COLOR)),
    YELLOW(Action.SHOW_TEXT, Color.parseColor(YELLOW_COLOR)),
    GREEN(Action.SHOW_IMAGE, Color.GREEN),
    CYAN(Action.SHOW_TEXT, Color.CYAN),
    BLUE(Action.SHOW_IMAGE, Color.BLUE),
    PURPLE(Action.SHOW_TEXT, Color.parseColor(PURPLE_COLOR))

}