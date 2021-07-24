import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from './user';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${this.apiServerUrl}/alluser`);
  } 

  public addUser(user: User): Observable<User> {
    return this.http.post<User>(`${this.apiServerUrl}/adduser`, user);
  }

  public updateUser(userID: number, user: User): Observable<string> {
    return this.http.put<string>(`${this.apiServerUrl}/updateuser/${userID}`, user);
  }

  public deleteUser(userID: number): Observable<string> {
    return this.http.delete<string>(`${this.apiServerUrl}/deleteuser/${userID}`);
  }
}
