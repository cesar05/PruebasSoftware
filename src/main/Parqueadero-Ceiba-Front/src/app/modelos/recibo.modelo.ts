export class Recibo {
    placa: string;
    cilindraje: number;
    fechaEntrada: string;
    valorPagar: number;

    constructor(placa, cilindraje, fechaEntrada, valorPagar) {
        this.placa = placa;
        this.cilindraje = cilindraje;
        this.fechaEntrada = fechaEntrada;
        this.valorPagar = valorPagar;
    }
}