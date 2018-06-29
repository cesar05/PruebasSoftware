export class Respuesta {
    msj: string;
    estado: boolean;

    constructor(msj, estado) {
        this.msj = msj;
        this.estado = estado;
    }
}