import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ClientesService } from 'src/app/clientes.service';
import { Cliente } from 'src/app/clientes/cliente';
import { ServicoPrestadoService } from 'src/app/servico-prestado.service';
import { ServicoPrestadoListaComponent } from '../servico-prestado-lista/servico-prestado-lista.component';
import { ServicoPrestado } from '../servicoPrestado';

@Component({
  selector: 'app-servico-prestado-form',
  templateUrl: './servico-prestado-form.component.html',
  styleUrls: ['./servico-prestado-form.component.css']
})
export class ServicoPrestadoFormComponent implements OnInit {

  clientes: Cliente[] = [];
  servico!: ServicoPrestado;
  success: boolean = false;
  errors: string[] | null | undefined;

  constructor(private clienteService: ClientesService, private serviceServicoPrestado: ServicoPrestadoService, private router: Router) {
    this.servico = new ServicoPrestado();
   }

  ngOnInit(): void {
    this.clienteService.getClientes()
    .subscribe( response => this.clientes = response);
  }

  onSubmit(){
    console.log(this.servico);
    this.serviceServicoPrestado.salvar(this.servico)
    .subscribe(response => {
      this.success = true;
      this.errors = null;
      this.servico = new ServicoPrestado();
    }, errorResponse => {
      this.success = false;
      this.errors = errorResponse.error.errors;
    });
  }

  voltarParaListagem(){
    this.router.navigate(['/servico-prestado-lista']);
  }
}
