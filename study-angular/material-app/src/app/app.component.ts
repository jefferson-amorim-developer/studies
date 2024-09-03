import { Component } from '@angular/core';
import { MatIconButton } from '@angular/material/button';
import { MatIcon } from '@angular/material/icon';
import { MatListItem, MatNavList } from '@angular/material/list';
import {
  MatSidenav,
  MatSidenavContainer,
  MatSidenavContent,
} from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet,

    MatSidenav,
    MatSidenavContainer,
    MatSidenavContent,
    MatListItem,
    MatToolbarModule,
    MatIcon,
    MatIconButton,
    MatNavList,
  ],
  template: `
    <mat-sidenav-container fullscreen>
      <mat-sidenav #sidenav mode="side" opened>
        <mat-nav-list (click)="sidenav.close()">
          <h4>My App</h4>
          <a mat-list-item routerLink="/">
            <mat-icon>library_books</mat-icon>
            <label>Courses</label>
          </a>
        </mat-nav-list>
      </mat-sidenav>
      <mat-sidenav-content>
        <mat-toolbar color="primary">
          <button mat-icon-button (click)="sidenav.open()">
            <mat-icon>menu</mat-icon>
          </button>
        </mat-toolbar>

        <router-outlet />
      </mat-sidenav-content>
    </mat-sidenav-container>
  `,
  styles: [],
})
export class AppComponent {
  title = 'material-app';
}
