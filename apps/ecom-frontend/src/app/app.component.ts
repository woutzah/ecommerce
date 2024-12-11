import { Component, inject, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { fontAwesomeIcons } from './shared/font-awesome-icons';
import { FaConfig, FaIconComponent, FaIconLibrary } from '@fortawesome/angular-fontawesome';
import { NavbarComponent } from './layout/navbar/navbar.component';
import { FooterComponent } from './layout/footer/footer.component';

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

  ngOnInit() {
    this.initFontAwesome();
  }

  private initFontAwesome() {
    this.faConfig.defaultPrefix = 'far';
    this.faIconlibrary.addIcons(...fontAwesomeIcons);
  }
}
