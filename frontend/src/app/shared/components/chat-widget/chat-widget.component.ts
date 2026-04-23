import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../../environments/environment';

interface Message {
  text: string;
  isUser: boolean;
  timestamp: Date;
}

@Component({
  selector: 'app-chat-widget',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './chat-widget.component.html',
  styleUrls: ['./chat-widget.component.css']
})
export class ChatWidgetComponent implements OnInit {
  isOpen = false;
  messages: Message[] = [];
  userInput = '';
  isLoading = false;

  quickButtons = [
    'What can this assistant do?',
    'I have an issue',
    'Show me available rooms',
    'How do I make a reservation?'
  ];

  constructor(private http: HttpClient) {}

  ngOnInit() {
    // Welcome message
    this.addBotMessage('Hello! I\'m your virtual housing assistant. How can I help you today?');
  }

  toggleChat() {
    this.isOpen = !this.isOpen;
  }

  closeChat() {
    this.isOpen = false;
  }

  sendMessage(text?: string) {
    const messageText = text || this.userInput.trim();
    
    if (!messageText) return;

    // Add user message
    this.addUserMessage(messageText);
    this.userInput = '';
    this.isLoading = true;

    // Call backend chatbot API
    this.http.post<{ response: string }>(`${environment.apiUrl}/api/chatbot/ask`, {
      message: messageText
    }).subscribe({
      next: (response) => {
        this.addBotMessage(response.response);
        this.isLoading = false;
      },
      error: (error) => {
        console.error('Chatbot error:', error);
        this.addBotMessage('Sorry, I\'m having trouble connecting. Please try again later.');
        this.isLoading = false;
      }
    });
  }

  sendQuickMessage(text: string) {
    this.sendMessage(text);
  }

  private addUserMessage(text: string) {
    this.messages.push({
      text,
      isUser: true,
      timestamp: new Date()
    });
    this.scrollToBottom();
  }

  private addBotMessage(text: string) {
    this.messages.push({
      text,
      isUser: false,
      timestamp: new Date()
    });
    this.scrollToBottom();
  }

  private scrollToBottom() {
    setTimeout(() => {
      const messagesContainer = document.querySelector('.chat-messages');
      if (messagesContainer) {
        messagesContainer.scrollTop = messagesContainer.scrollHeight;
      }
    }, 100);
  }

  onKeyPress(event: KeyboardEvent) {
    if (event.key === 'Enter' && !event.shiftKey) {
      event.preventDefault();
      this.sendMessage();
    }
  }
}
