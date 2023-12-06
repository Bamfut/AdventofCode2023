package day2

class Cubes(var red: Int, var green: Int, var blue: Int) {
    fun replaceIfBigger(red: Int, green: Int, blue: Int) {
        if (red > this.red) {
            this.red = red
        }

        if (green > this.green) {
            this.green = green
        }

        if (blue > this.blue) {
            this.blue = blue
        }
    }
}