/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.anynet.anybot.bot;

/**
 *
 * @author sim
 */
public class ChatMessage extends ChatEvent {

   private String nick;
   private String ident;
   private String host;
   private String channel;
   private String message;


   public ChatMessage(Bot bot)
   {
      super(bot);
   }


   public String getNick() {
      return nick;
   }

   public String getIdent() {
      return ident;
   }

   public String getHost() {
      return host;
   }

   public String getChannel() {
      return channel;
   }

   public String getMessage() {
      return message;
   }

   public void setNick(String nick) {
      this.nick = nick;
   }

   public void setIdent(String ident) {
      this.ident = ident;
   }

   public void setHost(String host) {
      this.host = host;
   }

   public void setChannel(String channel) {
      this.channel = channel;
   }

   public void setMessage(String message) {
      this.message = message;
   }


   public boolean isChannelSet()
   {
      return (this.getChannel()!=null && this.getChannel().startsWith("#") && this.getChannel().length()>1);
   }

   public boolean isNickSet()
   {
      return (this.getNick()!=null && this.getNick().length()>0);
   }

   public boolean isChannelMessage()
   {
      return (this.isChannelSet() && this.isNickSet());
   }

   public boolean isPrivateMessage()
   {
      return (this.isChannelSet()==false && this.isNickSet());
   }

   public void respond(String message, boolean action) throws UnsupportedOperationException
   {
      if(this.isChannelSet())
      {
         this.respondChannel(message, action);
      }
      else if(this.isNickSet())
      {
         this.respondNick(message, action);
      }
      else
      {
         throw new UnsupportedOperationException("No channel and no nick available");
      }
   }

   public void respond(String message)
   {
      this.respond(message, false);
   }

   public void respond(String[] messages, boolean action)
   {
      for(String imessage : messages)
      {
         this.respond(imessage, action);
      }
   }

   public void respond(String[] messages)
   {
      this.respond(messages, false);
   }

   public void respondChannel(String message, boolean action) throws UnsupportedOperationException
   {
      if(!this.isChannelSet())
      {
         throw new UnsupportedOperationException("No channel name available");
      }
      this.sendMessage(this.getChannel(), message, action);
   }

   public void respondChannel(String message)
   {
      this.respondChannel(message, false);
   }

   public void respondChannel(String[] messages, boolean action)
   {
      for(String imessage : messages)
      {
         this.respondChannel(imessage, action);
      }
   }

   public void respondChannel(String[] messages)
   {
      this.respondChannel(messages, false);
   }

   public void respondNick(String message, boolean action) throws UnsupportedOperationException
   {
      if(!this.isNickSet())
      {
         throw new UnsupportedOperationException("No nick available");
      }
      this.sendMessage(this.getNick(), message, action);
   }

   public void respondNick(String message)
   {
      this.respondNick(message, false);
   }

   public void respondNick(String[] messages, boolean action)
   {
      for(String imessage : messages)
      {
         this.respondNick(imessage, action);
      }
   }

   public void respondNick(String[] messages)
   {
      this.respond(messages, false);
   }

}