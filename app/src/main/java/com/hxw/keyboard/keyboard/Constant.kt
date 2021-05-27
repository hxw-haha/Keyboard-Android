package com.hxw.keyboard.keyboard

import com.hxw.keyboard.utils.DisplayUtil


/**
 * <p>文件描述：</p>
 * <p>作者：hanxw</p>
 * <p>创建时间：2021/3/29</p>
 * <p>更改时间：2021/3/29</p>
 * <p>版本号：1</p>
 */
object Constant {
    /**
     * 默认最大金额小数点后几位
     */
    var defaultMoneyPointMaxLength = 2

    /**
     * item高度
     */
    var itemHeight = DisplayUtil.dp2px(40f)

    /**
     * item margin
     */
    var itemMarginSize = DisplayUtil.dp2px(4f)

    var blankFlag = " "
    var pointFlag = "•"
    var pointInterval = "."
    var numberFlag = "123"
    var letterFlag = "ABC"
    var signFlag = "#+="
    var caseFlag = "大小"
    var clearFlag = "清除"
    var deleteFlag = "delete"

    /**
     * 数字集合
     */
    var keyboardNumberList = listOf(
        "1",
        "2",
        "3",
        "4",
        "5",
        "6",
        "7",
        "8",
        "9",
        "0"
    )

    /**
     * 大写字母集合
     */
    var keyboardLetterBigList = listOf(
        "Q",
        "W",
        "E",
        "R",
        "T",
        "Y",
        "U",
        "I",
        "O",
        "P",
        "A",
        "S",
        "D",
        "F",
        "G",
        "H",
        "J",
        "K",
        "L",
        "Z",
        "X",
        "C",
        "V",
        "B",
        "N",
        "M"
    )

    /**
     * 小写字母集合
     */
    var keyboardLetterSmallList = listOf(
        "q",
        "w",
        "e",
        "r",
        "t",
        "y",
        "u",
        "i",
        "o",
        "p",
        "a",
        "s",
        "d",
        "f",
        "g",
        "h",
        "j",
        "k",
        "l",
        "z",
        "x",
        "c",
        "v",
        "b",
        "n",
        "m"
    )

    /**
     * 符号集合
     */
    var keyboardSignList = listOf(
        "!",
        "@",
        "#",
        "$",
        "%",
        "^",
        "&",
        "*",
        "(",
        ")",
        "'",
        "\"",
        "=",
        "_",
        ":",
        ";",
        "?",
        "~",
        "|",
        "+",
        "-",
        "\\",
        "/",
        "[",
        "]",
        "{",
        "}",
        ",",
        ".",
        "<",
        ">",
        "`"
    )
}