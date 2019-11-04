import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Card[] cards = new Card[52];
        fillUp(cards);
        shuffleNtimes(cards, 3);


        System.out.println(Arrays.toString(cards));
        System.out.println("sorted");
        System.out.println(Arrays.toString(radixSort(cards)));
    }

    public static Card[] radixSort(Card[] cards) {

        Integer maxCardCode = Card.SPADES.getCode();
        for (int position = 1; maxCardCode / position > 0; position *= 10) {
            cards = countingSort(cards, position);
        }
        return cards;

    }

    private static Card[] countingSort(Card[] cards, int position) {
        int[] count = new int[10];
        Card[] sortedCards = new Card[cards.length];
        for (Card card : cards) {
            count[(card.getCode() / position) % 10]++;
        }
        for (int i = 1; i < count.length; i++) {
            count[i] = count[i] + count[i - 1];
        }
        for (int i = sortedCards.length - 1; i >= 0; i--) {
            sortedCards[--count[(cards[i].getCode() / position) % 10]] = cards[i];
        }
        return sortedCards;
    }


    private static void fillUp(Card[] cards) {
        int from = 0;
        from = fill(cards, Card.CLUB, from);
        from = fill(cards, Card.DIAMOND, from);
        from = fill(cards, Card.HEART, from);
        fill(cards, Card.SPADES, from);


    }

    private static void shuffleNtimes(Card[] cards, int n) {
        for (int i = 0; i < n; i++) {
            knuthShuffle(cards);
        }
    }

    private static int fill(Card[] cards, Card card, int from) {
        int i;
        for (i = from; i < from + 13; i++) {
            cards[i] = card;
        }
        return i;
    }

    private static void exchange(int i, int j, Card[] cards) {
        Card card = cards[i];
        cards[i] = cards[j];
        cards[j] = card;
    }

    public static void knuthShuffle(Card[] cards) {
        Random random = new Random();
        for (int i = cards.length -1; i > 1; i--) {
            int r = random.nextInt(i - 1);
            exchange(i, r, cards);
        }
    }


    enum Card {
        DIAMOND(10, "Diamond"),
        CLUB(11, "club"),
        HEART(12, "Heart"),
        SPADES(13, "SPADES");


        private Integer code;
        private String card;


        Card(Integer code, String card) {
            this.code = code;
            this.card = card;
        }

        public Integer getCode() {
            return code;
        }

        public String getCard() {
            return card;
        }
    }
}
