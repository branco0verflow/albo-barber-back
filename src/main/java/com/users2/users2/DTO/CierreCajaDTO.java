package com.users2.users2.DTO;


import java.math.BigDecimal;

public class CierreCajaDTO {
    private BigDecimal facturacion;
    private BigDecimal gastos;
    private BigDecimal cierreCaja;
    private BigDecimal noMonetario;

    public CierreCajaDTO(BigDecimal facturacion, BigDecimal gastos, BigDecimal cierreCaja, BigDecimal noMonetario) {
        this.facturacion = facturacion;
        this.gastos = gastos;
        this.cierreCaja = cierreCaja;
        this.noMonetario = noMonetario;
    }

    public CierreCajaDTO(){}

    public BigDecimal getFacturacion() {
        return facturacion;
    }

    public void setFacturacion(BigDecimal facturacion) {
        this.facturacion = facturacion;
    }

    public BigDecimal getGastos() {
        return gastos;
    }

    public void setGastos(BigDecimal gastos) {
        this.gastos = gastos;
    }

    public BigDecimal getCierreCaja() {
        return cierreCaja;
    }

    public void setCierreCaja(BigDecimal cierreCaja) {
        this.cierreCaja = cierreCaja;
    }

    public BigDecimal getNoMonetario() {
        return noMonetario;
    }

    public void setNoMonetario(BigDecimal noMonetario) {
        this.noMonetario = noMonetario;
    }

}
