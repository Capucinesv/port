package com.example.port.Model;

public class AllAction {
    private Long Actions_id;

    private String UrlApi;

    private String Symbol;

    public AllAction(Long actions_id, String urlApi, String symbol) {
        Actions_id = actions_id;
        UrlApi = urlApi;
        Symbol = symbol;
    }

    public Long getActions_id() {
        return Actions_id;
    }

    public void setActions_id(Long actions_id) {
        Actions_id = actions_id;
    }

    public String getUrlApi() {
        return UrlApi;
    }

    public void setUrlApi(String urlApi) {
        UrlApi = urlApi;
    }

    public String getSymbol() {
        return Symbol;
    }

    public void setSymbol(String symbol) {
        Symbol = symbol;
    }

    @Override
    public String toString() {
        return "AllAction{" +
                "Actions_id=" + Actions_id +
                ", UrlApi='" + UrlApi + '\'' +
                ", Symbol='" + Symbol + '\'' +
                '}';
    }
}