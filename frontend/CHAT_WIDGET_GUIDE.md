# Chat Widget Component - Usage Guide

## Overview
A beautiful, reusable standalone chat widget component built with Angular 16.2 and Bootstrap 5 that integrates with your existing Gemini AI chatbot backend.

## Features
✅ Floating chat bubble button (bottom-right)
✅ Smooth open/close animations
✅ Scrollable messages area
✅ User messages (right, purple gradient)
✅ Bot messages (left, white)
✅ Typing indicator animation
✅ Quick action buttons
✅ Send button with icon
✅ Enter key to send
✅ Fully responsive
✅ Integrates with `/api/chatbot/ask` endpoint

## Files Created
```
frontend/src/app/shared/components/chat-widget/
├── chat-widget.component.html
├── chat-widget.component.css
└── chat-widget.component.ts
```

## Installation
Already integrated! The widget is now available on all pages.

## How It Works

### 1. Chat Bubble
- Fixed position at bottom-right corner
- Click to open chat window
- Smooth scale animation

### 2. Chat Window
- Header with "Virtual Assistant" title
- Close button (×)
- Scrollable messages area
- Quick action buttons (shown initially)
- Input field with send button

### 3. Backend Integration
Calls your existing chatbot API:
```typescript
POST /api/chatbot/ask
Body: { message: "user question" }
Response: { response: "bot answer" }
```

## Customization

### Change Colors
Edit `chat-widget.component.css`:
```css
/* Purple gradient - change to your brand colors */
background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
```

### Change Quick Buttons
Edit `chat-widget.component.ts`:
```typescript
quickButtons = [
  'Your custom button 1',
  'Your custom button 2',
  'Your custom button 3'
];
```

### Change Welcome Message
Edit `chat-widget.component.ts` in `ngOnInit()`:
```typescript
this.addBotMessage('Your custom welcome message');
```

## Usage in Other Components (Optional)

If you want to use it in specific pages only:

1. Remove from `app.component.html`
2. Add to specific component template:
```html
<app-chat-widget></app-chat-widget>
```

## Styling Details

### Animations
- Chat bubble: scale + fade
- Chat window: scale + slide up
- Messages: slide in from bottom
- Typing indicator: bouncing dots

### Responsive
- Desktop: 380px width
- Mobile: Full screen with margins

### Colors
- Primary: Purple gradient (#667eea → #764ba2)
- Background: Light gray (#f8f9fa)
- User messages: Purple gradient
- Bot messages: White with shadow

## Testing

1. Start backend: `./mvnw spring-boot:run`
2. Start frontend: `npm start`
3. Click chat bubble (bottom-right)
4. Try quick buttons or type a message
5. Press Enter or click send button

## Backend Requirements

Make sure your backend is running and the Gemini API key is set in `.env`:
```
GEMINI_API_KEY=your_api_key_here
```

## Browser Support
- Chrome ✅
- Firefox ✅
- Safari ✅
- Edge ✅

## Notes
- Component is standalone (Angular 16.2 feature)
- Uses Bootstrap 5 icons
- No additional dependencies needed
- Backend endpoint: `/api/chatbot/ask`
- Proxy configured in `proxy.conf.json`
