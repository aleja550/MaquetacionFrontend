import { Component, signal } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('RemindMe+');
  protected readonly isMobileMenuOpen = signal(false);
  protected readonly currentRoute = signal('dashboard');

  constructor(private router: Router) {}

  protected toggleMobileMenu(): void {
    this.isMobileMenuOpen.update(current => !current);
  }

  protected onMenuItemClick(menuItem: string): void {
    this.currentRoute.set(menuItem);
    this.router.navigate([`/${menuItem}`]);
  }

  protected isActiveRoute(route: string): boolean {
    return this.currentRoute() === route;
  }
}
