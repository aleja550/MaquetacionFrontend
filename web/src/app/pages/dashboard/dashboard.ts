import { Component, signal } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  imports: [],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css'
})
export class Dashboard {
  constructor(private router: Router) {}
  protected readonly activeReminders = signal(3);
  protected readonly completedToday = signal(8);
  protected readonly createdCategories = signal(5);

  protected onNewReminder(): void {
    this.router.navigate(['/crear-recordatorio']);
  }

  protected testMathAlarm(): void {
    this.router.navigate(['/alarma-matematica']);
  }

  protected testMotivationalAlarm(): void {
    this.router.navigate(['/alarma-motivacional']);
  }
}
