import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { UserService } from '../user.service';
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  public users!: User[];

  constructor(private userService: UserService){}

  ngOnInit(){
    this.getUsers();
  }
  public getUsers(): void{
    this.userService.getUsers().subscribe(
      (response: User[]) => {
        this.users = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onAddUser(addForm: NgForm): void {

    document.getElementById('add-user-form')?.click();
    
    this.userService.addUser(addForm.value).subscribe(
      (response: User) => {
        console.log(response);
        this.getUsers();
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        addForm.reset();
      }
    );
  }

  public onOpenModal2(mode: string): void{
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if(mode === 'add'){
      button.setAttribute('data-target', '#addUserModal');
    }
    container?.appendChild(button);
    button.click();
    }

}
