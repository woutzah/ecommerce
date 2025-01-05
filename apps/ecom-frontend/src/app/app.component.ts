import { Component, inject, OnInit, PLATFORM_ID } from '@angular/core';
import { RouterModule } from '@angular/router';
import { fontAwesomeIcons } from './shared/font-awesome-icons';
import { FaConfig, FaIconComponent, FaIconLibrary } from '@fortawesome/angular-fontawesome';
import { NavbarComponent } from './layout/navbar/navbar.component';
import { FooterComponent } from './layout/footer/footer.component';
import { Oauth2Service } from './auth/oauth2.service';
import { isPlatformBrowser } from '@angular/common';

@Component({
  standalone: true,
  imports: [RouterModule, FaIconComponent, NavbarComponent, FooterComponent],
  selector: 'ecom-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})
export class AppComponent implements OnInit {
  private faIconlibrary = inject(FaIconLibrary);
  private faConfig = inject(FaConfig);
  private oauth2Service = inject(Oauth2Service);

  private platformId = inject(PLATFORM_ID);

  constructor() {
    if(isPlatformBrowser(this.platformId)){
      this.oauth2Service.initAuthentication();
    }

    this.oauth2Service.connectedUserQuery = this.oauth2Service.fetch()
  }

  ngOnInit() {
    this.initFontAwesome();
  }

  private initFontAwesome() {
    this.faConfig.defaultPrefix = 'far';
    this.faIconlibrary.addIcons(...fontAwesomeIcons);
  }
}
