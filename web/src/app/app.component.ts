import { Component, OnInit } from '@angular/core';
import { User } from './user';
import { UserService } from './user.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{

  public users?: User[];
  public editUser?: User;
  public deleteUser?: User;

  constructor(private userService: UserService){}

  ngOnInit(){
    this.getUsers();
  }

  public getUsers(): void{
    this.userService.getUsers().subscribe(
      (response: User[]) => {
        console.log(response);
        this.users = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onUpdateUser(user: User): void {
    this.userService.updateUser(user).subscribe(
      (response: User) => {
        console.log(response);
        this.getUsers();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onDeleteUser(userId: number): void {
    this.userService.deleteUser(userId).subscribe(
      (response: string) => {
        console.log(response);
        this.getUsers();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  

public onOpenModal(user: User, mode: string): void{
const container = document.getElementById('main-container');
const button = document.createElement('button');
button.type = 'button';
button.style.display = 'none';
button.setAttribute('data-toggle', 'modal');

if(mode === 'edit'){
  this.editUser = user;
  console.log(user);
  button.setAttribute('data-target', '#updateUserModal');
}
if(mode === 'delete'){
  button.setAttribute('data-target', '#deleteUserModal');
}
container?.appendChild(button);
button.click();
}

}
