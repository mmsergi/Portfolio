package mm.sergi.portfolio;

/**
 * Created by sergi on 04/12/2017.
 */

public class Coin {

    private String id;
    private String name;
    private String symbol;
    private int rank;
    private float price_usd;
    private float price_btc;
    private float volume_usd_24h;
    private float market_cap_usd;
    private float available_supply;
    private float total_supply;
    private float max_supply;
    private float percent_change_1h;
    private float percent_change_24h;
    private float percent_change_7d;
    private float last_updated;

    private float price_div;
    private float volume_div_24h;
    private float market_cap_div;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public float getPrice_usd() {
        return price_usd;
    }

    public void setPrice_usd(float price_usd) {
        this.price_usd = price_usd;
    }

    public float getPrice_btc() {
        return price_btc;
    }

    public void setPrice_btc(float price_btc) {
        this.price_btc = price_btc;
    }

    public float getVolume_usd_24h() {
        return volume_usd_24h;
    }

    public void setVolume_usd_24h(float volume_usd_24h) {
        this.volume_usd_24h = volume_usd_24h;
    }

    public float getMarket_cap_usd() {
        return market_cap_usd;
    }

    public void setMarket_cap_usd(float market_cap_usd) {
        this.market_cap_usd = market_cap_usd;
    }

    public float getAvailable_supply() {
        return available_supply;
    }

    public void setAvailable_supply(float available_supply) {
        this.available_supply = available_supply;
    }

    public float getTotal_supply() {
        return total_supply;
    }

    public void setTotal_supply(float total_supply) {
        this.total_supply = total_supply;
    }

    public float getMax_supply() {
        return max_supply;
    }

    public void setMax_supply(float max_supply) {
        this.max_supply = max_supply;
    }

    public float getPercent_change_1h() {
        return percent_change_1h;
    }

    public void setPercent_change_1h(float percent_change_1h) {
        this.percent_change_1h = percent_change_1h;
    }

    public float getPercent_change_24h() {
        return percent_change_24h;
    }

    public void setPercent_change_24h(float percent_change_24h) {
        this.percent_change_24h = percent_change_24h;
    }

    public float getPercent_change_7d() {
        return percent_change_7d;
    }

    public void setPercent_change_7d(float percent_change_7d) {
        this.percent_change_7d = percent_change_7d;
    }

    public float getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(float last_updated) {
        this.last_updated = last_updated;
    }

    public float getPrice_div() {
        return price_div;
    }

    public void setPrice_div(float price_div) {
        this.price_div = price_div;
    }

    public float getVolume_div_24h() {
        return volume_div_24h;
    }

    public void setVolume_div_24h(float volume_div_24h) {
        this.volume_div_24h = volume_div_24h;
    }

    public float getMarket_cap_div() {
        return market_cap_div;
    }

    public void setMarket_cap_div(float market_cap_div) {
        this.market_cap_div = market_cap_div;
    }
}