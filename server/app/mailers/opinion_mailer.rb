class OpinionMailer < ApplicationMailer
  default from: 'no-reply@b00dle.com'
  def invitation email, link
    mail(to: email, subject: 'Link: ' + link)
  end
end
