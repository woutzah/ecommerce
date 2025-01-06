import { Component, inject } from '@angular/core';
import { CommonModule, NgOptimizedImage } from '@angular/common';
import { RouterLink } from '@angular/router';
import { FaIconComponent } from '@fortawesome/angular-fontawesome';
import { Oauth2Service } from '../../auth/oauth2.service';

@Component({
  selector: 'ecom-navbar',
  standalone: true,
  imports: [CommonModule, RouterLink, FaIconComponent, NgOptimizedImage],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss',
})
export class NavbarComponent {
  oauth2Service = inject(Oauth2Service);

  connectedUserQuery = this.oauth2Service.connectedUserQuery;

  login(): void {
    this.closeDropDownMenu();
    this.oauth2Service.login();
  }

  logout(): void {
    this.closeDropDownMenu();
    this.oauth2Service.logout();
  }

  isConnected(): boolean {
    return (
      this.connectedUserQuery?.status() == 'success' &&
      this.connectedUserQuery?.data()?.email !==
        this.oauth2Service.NOT_CONNECTED
    );
  }

  closeDropDownMenu() {
    let bodyElement = document.activeElement as HTMLBodyElement;

    if (bodyElement) {
      bodyElement.blur();
    }
  }
}
