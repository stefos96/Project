package Dice;

/**
 * Also is HitDie
 */
public enum Dice{
    D2(2),
    D3(3),
    D4(4),
    D6(6),
    D8(8),
    D10(10),
    D12(12),
    D20(20);

    public int number;

    Dice(int number){
        this.number = number;
    }

    /**
     * Roll the dice
     */
    public int roll() {
        return (int) ((Math.random() * number + 1));
    }
}