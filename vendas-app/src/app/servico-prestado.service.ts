import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { servicoPrestadoBusca } from './servico-prestado/servico-prestado-lista/servicoPrestadoBusca';
import { ServicoPrestado } from './servico-prestado/servicoPrestado';

@Injectable({
  providedIn: 'root'
})
export class ServicoPrestadoService {

  apiURL: string = environment.apiURLBase + '/api/servico-prestado';

  constructor(private http: HttpClient) { 

  }

  salvar (servicoPrestado : ServicoPrestado) : Observable<ServicoPrestado> {
    return this.http.post<ServicoPrestado>(this.apiURL, servicoPrestado);
  }

  consultar (id:number,nome: string, mes: number) : Observable<servicoPrestadoBusca[]>{ 
    const httpParams = new HttpParams().set("id", id ? id.toString() : '0').set("nome", nome ? nome : '').set("mes", mes ? mes.toString() : '0');
    const url = this.apiURL + "?" + httpParams.toString();
    return this.http.get<any>(url)
  }
}
