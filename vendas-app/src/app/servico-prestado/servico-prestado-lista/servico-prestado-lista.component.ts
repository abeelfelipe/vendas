import { Component, OnInit } from '@angular/core';
import { ServicoPrestadoService } from 'src/app/servico-prestado.service';
import { servicoPrestadoBusca } from './servicoPrestadoBusca';

@Component({
  selector: 'app-servico-prestado-lista',
  templateUrl: './servico-prestado-lista.component.html',
  styleUrls: ['./servico-prestado-lista.component.css']
})
export class ServicoPrestadoListaComponent implements OnInit {

  nome!:string;
  mes!: number;
  id!: number;
  meses: number[];
  listaServicos: servicoPrestadoBusca[] = [];
  message?: string | null;
  constructor(private service:ServicoPrestadoService) {
    this.meses = [1,2,3,4,5,6,7,8,9,10,11,12];
   }

  ngOnInit(): void {
  }

  consultar(){
    this.service.consultar(this.id, this.nome, this.mes)
    .subscribe(response => {
        this.listaServicos = response;
        if(this.listaServicos.length<=0) { 
          this.message = "Nenhum Registro Encontrado."
        } else {
          this.message = null;
        }
      });
  }
}
