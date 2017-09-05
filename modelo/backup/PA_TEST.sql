--
-- PostgreSQL database dump
--

-- Dumped from database version 9.4.0
-- Dumped by pg_dump version 9.4.0
-- Started on 2017-09-05 10:22:43

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET search_path = "FormulaVR", pg_catalog;

--
-- TOC entry 2143 (class 0 OID 102340)
-- Dependencies: 182
-- Data for Name: T_PA_systemParameters; Type: TABLE DATA; Schema: FormulaVR; Owner: postgres
--

INSERT INTO "T_PA_systemParameters" VALUES ('1705091000999', ' ', ' ', 'SYS', 'SYS', 'VERSION', '17164');
INSERT INTO "T_PA_systemParameters" VALUES ('1705091000999', ' ', ' ', 'SYS', 'SYS', 'MG_URL', 'https://driver.formulavr.net/');
INSERT INTO "T_PA_systemParameters" VALUES ('1705091000999', ' ', ' ', 'CENTRAL', 'SYS', 'RS_MIN_FEC', '2000-12-31');
INSERT INTO "T_PA_systemParameters" VALUES ('1705091000999', ' ', ' ', 'SYS', 'SYS', 'DOMAIN', 'formulavr.net');
INSERT INTO "T_PA_systemParameters" VALUES ('1705091000999', ' ', ' ', 'SYS', 'SYS', 'URL_WEB_PAGE', 'http://localhost:8080/FormulaVR/');
INSERT INTO "T_PA_systemParameters" VALUES ('1705091000999', ' ', ' ', 'SYS', 'SYS', 'SMTP_HOST_NAME', 'smtp.formulavr.net');
INSERT INTO "T_PA_systemParameters" VALUES ('1705091000999', ' ', ' ', 'SYS', 'SYS', 'SMTP_AUTH_USER', 'glsmtp');
INSERT INTO "T_PA_systemParameters" VALUES ('1705091000999', ' ', ' ', 'SYS', 'SYS', 'SMTP_AUTH_PWD', 'F0rmula.vr');
INSERT INTO "T_PA_systemParameters" VALUES ('1705091000999', ' ', ' ', 'SYS', 'SYS', 'SMTP_MAIL_PORT', '465');
INSERT INTO "T_PA_systemParameters" VALUES ('1705091000999', ' ', ' ', 'SYS', 'SYS', 'SMTP_MAIL_REGISTER_FROM', 'no-reply');
INSERT INTO "T_PA_systemParameters" VALUES ('1705091000999', ' ', ' ', 'SYS', 'SYS', 'SMTP_MAIL_REGISTER_SUBJECT', 'FORMULA-VR: Registro nuevo usuario');
INSERT INTO "T_PA_systemParameters" VALUES ('1705091000999', ' ', ' ', 'SYS', 'SYS', 'SMTP_MAIL_CHANGE_PASSWORD_FROM', 'no-reply');
INSERT INTO "T_PA_systemParameters" VALUES ('1705091000999', ' ', ' ', 'SYS', 'SYS', 'SMTP_MAIL_CHANGE_PASSWORD_SUBJECT', 'FORMULA-VR: Cambiar password');
INSERT INTO "T_PA_systemParameters" VALUES ('1705091000999', ' ', ' ', 'SYS', 'SYS', 'SMTP_MAIL_RESERVE_CONFIRM_FROM', 'no-reply');
INSERT INTO "T_PA_systemParameters" VALUES ('1705091000999', ' ', ' ', 'SYS', 'SYS', 'SMTP_MAIL_RESERVE_CONFIRM_SUBJECT', 'FORMULA-VR: Reserva');
INSERT INTO "T_PA_systemParameters" VALUES ('1705091000999', ' ', ' ', 'SYS', 'SYS', 'MAIL_NEW_USER_REGISTRATION', '<?xml version="1.0" encoding="UTF-8" ?><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"><html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /><title>Registro nuevo usuario</title><style type="text/css">/* CLIENT-SPECIFIC STYLES */	#outlook a {	padding: 0;	}	/* Force Outlook to provide a "view in browser" message */	.ReadMsgBody {	width: 100%;	}		.ExternalClass {	width: 100%;	}	/* Force Hotmail to display emails at full width */	.ExternalClass, .ExternalClass p, .ExternalClass span, .ExternalClass font,	.ExternalClass td, .ExternalClass div {	line-height: 100%;	}	/* Force Hotmail to display normal line spacing */	body, table, td, a {	-webkit-text-size-adjust: 100%;	-ms-text-size-adjust: 100%;	}	/* Prevent WebKit and Windows mobile changing default text sizes */	table, td {	mso-table-lspace: 0pt;	mso-table-rspace: 0pt;	}	/* Remove spacing between tables in Outlook 2007 and up */	img {	-ms-interpolation-mode: bicubic;	}	/* Allow smoother rendering of resized image in Internet Explorer */	/* RESET STYLES */	body {	margin: 0;	padding: 0;	}		img {	border: 0;	height: auto;	line-height: 100%;	outline: none;	text-decoration: none;	}		table {	border-collapse: collapse !important;	}		a {	color: #999999;	text-decoration: underline;	}		body {	height: 100% !important;	margin: 0;	padding: 0;	width: 100% !important;	}	/* iOS BLUE LINKS */	.appleBody a {	color: #666666;	text-decoration: none;	}		.appleFooter a {	color: #999999;	text-decoration: none;	}	/* MOBILE STYLES */	@media screen and (max-width: 525px) {	/* ALLOWS FOR FLUID TABLES */	table[class="wrapper"] {	width: 100% !important;	}	/* ADJUSTS LAYOUT OF LOGO IMAGE */	td[class="logo"] {	text-align: left;	padding: 20px 0 20px 0 !important;	}	td[class="logo"] img {	margin: 0 auto !important;	}	/* USE THESE CLASSES TO HIDE CONTENT ON MOBILE */	td[class="mobile-hide"] {	display: none;	}	img[class="mobile-hide"] {	display: none !important;	}	img[class="img-max"] {	max-width: 100% !important;	height: auto !important;	}	/* FULL-WIDTH TABLES */	table[class="responsive-table"] {	width: 100% !important;	}	/* UTILITY CLASSES FOR ADJUSTING PADDING ON MOBILE */	td[class="padding"] {	padding: 10px 5% 15px 5% !important;	}	td[class="padding-copy"] {	padding: 15px 5% 0px 5% !important;	text-align: center;	}	td[class="padding-meta"] {	padding: 30px 5% 0px 5% !important;	text-align: center;	}	td[class="no-pad"] {	padding: 0 0 20px 0 !important;	}	td[class="no-padding"] {	padding: 0 !important;	}	td[class="section-padding"] {	padding: 10px 10px 10px 10px !important;	}	td[class="section-header"] {	padding: 10px 15px 10px 15px !important;	}	td[class="section-padding-bottom-image"] {	padding: 50px 15px 0 15px !important;	}	/* ADJUST BUTTONS ON MOBILE */	td[class="mobile-wrapper"] {	padding: 10px 5% 15px 5% !important;	}	table[class="mobile-button-container"] {	margin: 0 auto;	width: 100% !important;	}	a[class="mobile-button"] {	width: 80% !important;	padding: 15px !important;	border: 0 !important;	font-size: 16px !important;	}	}</style></head><body><!-- HEADER --><table  width="100%" align="center" bgcolor="#FFFFFF" cellspacing="0" cellpading="0" border="0" ><tr><td width="100%" align="center" bgcolor="#FFFFFF"><table  width="500" align="center" bgcolor="#FFFFFF" cellspacing="0" cellpading="0" border="0" ><!-- espacio 10 --><tr><td width="500" height="10" align="center" bgcolor="#FFFFFF"/></tr><!-- fin espacio --><tr><td width="500" align="center"  bgcolor="#FFFFFF"><table class="table" width="500" align="center" bgcolor="#FFFFFF" cellspacing="0" cellpading="0" border="0" ><tr><td width="187" align="center" bgcolor="#FFFFFF"/><td width="126" align="center" bgcolor="#FFFFFF"><img src="X=X=LOGO_IMG_SRC=X=X" alt="Fórmula VR" width="126" style="display:block;border:none;border.style:none;"/></td><td width="187" align="center" bgcolor="#FFFFFF"/></tr></table></td></tr><tr><td width="100%" align="center" bgcolor="#FFFFFF" style="font-family: Helvetica,Arial, Lucida Grande, sans-serif; font-size: 12px; color: #263238;line-height: 1.2;">Compite en un entorno virtual completamente inmersivo.</td></tr><!-- espacio 10 --><tr><td width="100%" height="10" align="center" bgcolor="#FFFFFF"/></tr><!-- fin espacio --></table></td></tr></table><!-- /HEADER --><!-- ONE COLUMN SECTION --><table border="0" cellpadding="0" cellspacing="0" width="100%"><tr><td bgcolor="#ff0000" align="center" style="padding: 10px 0 10px 0; font-family: Helvetica, Arial, sans-serif; font-size:26px; max-height: 0px; max-width: 0px; overflow: hidden; color:#FFFFFF;" class="section-padding">Registro en Fórmula VR</td></tr></table><!-- BODY --><table  width="100%" align="center" bgcolor="#FFFFFF" cellspacing="0" cellpading="0" border="0" ><tr><td width="100%" align="center" bgcolor="#FFFFFF"><table  width="500" align="center" bgcolor="#FFFFFF" cellspacing="0" cellpading="0" border="0" ><!-- espacio 20 --><tr><td width="500" height="20" align="center" bgcolor="#FFFFFF"/></tr><!-- fin espacio --><!-- content text left 16px--><tr><td width="500" align="left"  bgcolor="#FFFFFF" style="font-family: Helvetica,Arial, Lucida Grande, sans-serif; font-size: 16px; color: #263238;line-height: 1.4;"><p style="font-family: Helvetica,Arial, Lucida Grande, sans-serif;color: #263238;font-size:16px;line-height: 1.4;margin: 0px;">Bienvenido a Fórmula VR:</p></td></tr><!-- fin content text left --><!-- espacio 20 --><tr><td width="500" height="20" align="center" bgcolor="#FFFFFF"/></tr><!-- fin espacio --><!-- content text center 16px--><tr><td width="500" align="center"  bgcolor="#FFFFFF" style="font-family: Helvetica,Arial, Lucida Grande, sans-serif; font-size: 16px; color: #263238;line-height: 1.4;"><p style="font-family: Helvetica,Arial, Lucida Grande, sans-serif;color: #263238;font-size:16px;line-height: 1.4;margin: 0px;">Para continuar con tu registro en Fórmula VR y poder acceder a nuestros sofisticados dispositivos virtuales debes pulsar el siguiente enlace que te introducirá de nuevo en nuestra plataforma:</p></td></tr><!-- fin content text center --><!-- espacio 40 --><tr><td width="500" height="20" align="center" bgcolor="#FFFFFF"/></tr><!-- fin espacio --><!-- btn --><tr><td width="500" height="44px" align="center" style="font-family: Helvetica,Arial, Lucida Grande, sans-serif; font-size: 16px; color: #263238;line-height: 1.2;"><a href="X=X=CALLBACK=X=X" target="_blank" style="font-size: 16px; font-family: Helvetica, Arial, sans-serif; font-weight: normal; color: #ffffff; text-decoration: none; background-color: #ff0000 ; border-top: 15px solid #ff0000 ; border-bottom: 15px solid #ff0000 ; border-left: 25px solid #ff0000 ; border-right: 25px solid #ff0000 ; border-radius: 3px; -webkit-border-radius: 3px; -moz-border-radius: 3px; display: inline-block;" class="mobile-button"><b>X=X=TEXTO_BOTON=X=X</b></a></td></tr><!-- espacio 40 --><tr><td width="500" height="20" align="center" bgcolor="#FFFFFF"/></tr><!-- fin espacio --><!-- content text left 16px--><tr><td width="500" align="left"  bgcolor="#FFFFFF" style="font-family: Helvetica,Arial, Lucida Grande, sans-serif; font-size: 16px; color: #263238;line-height: 1.4;"><p style="font-family: Helvetica,Arial, Lucida Grande, sans-serif;color: #263238;font-size:16px;line-height: 1.4;margin: 0px;">Fórmula VR es un nuevo sistema de experiencias virtuales que utiliza lo más avanzado en tecnología de simulación inmersiva. <b>reg&iacute;strate, es gratis</b>.</p></td></tr><!-- fin content text center --><!-- espacio 80 --><tr><td width="500" height="40" align="center" bgcolor="#FFFFFF"/></tr><!-- fin espacio --></table></td></tr></table><!-- /BODY  --><!-- FOOTER --><table  width="100%" align="center" bgcolor="#FFFFFF" cellspacing="0" cellpading="0" border="0" ><tr><td width="100%" align="center" bgcolor="#FFFFFF"><table  width="500" align="center" bgcolor="#FFFFFF" cellspacing="0" cellpading="0" border="0"><!-- espacio 20 --><tr><td width="500" height="20" align="center" bgcolor="#FFFFFF"/></tr><!-- fin espacio --><!-- Informacion footer --><tr><td width="500" align="center" bgcolor="#FFFFFF" style="color: #AEAEAE; font-size: 12px;line-height: 1.4; font-family: Helvetica,Arial, Lucida Grande, sans-serif; "><p style="font-family: Helvetica,Arial, Lucida Grande, sans-serif;color: #AEAEAE;font-size:12px;line-height: 1.4;margin: 0px;">Y recuerda que, si tienes alguna duda o sugerencia sobre Fórmula VR, puedes ponerte en contacto con nosotros en <a href="mailto:info@formulavr.net?Subject=Contacto" target="_top" style="color:#999999;">info@formulavr.net</a>, en nuestra p&aacute;gina web <a href="https://www.formulavr.net" target="_top" style="color:#999999;">www.formulavr.net</a> a trav&eacute;s del chat o en nuestros tel&eacute;fonos de Atenci&oacute;n al Cliente 915792538 &oacute; 655284629. &#161;Estaremos encantados de ayudarte&#33;</p></td></tr><!-- fin Informacion footer --><!-- espacio 10 --><tr><td width="500" height="10" align="center" bgcolor="#FFFFFF"/></tr><!-- fin espacio --><tr><td width="500" align="center" bgcolor="#FFFFFF"><table  width="500" align="center" bgcolor="#FFFFFF" cellspacing="0" cellpading="0" border="0" ><tr><td width="50" align="center" bgcolor="#FFFFFF"/><td width="400" align="center" bgcolor="#FFFFFF" style="color: #AEAEAE; font-size: 12px;line-height: 1.2; font-family: Helvetica,Arial, Lucida Grande, sans-serif; "><small>Fórmula VR. Paseo de las Acacias 21. Coslada, 28821 Spain </small></td><td width="50" align="center" bgcolor="#FFFFFF"/></tr><tr><td width="50" align="center" bgcolor="#FFFFFF"/><td width="400" align="center" bgcolor="#FFFFFF" style="color: #AEAEAE; font-size: 12px;line-height: 1.2; font-family: Helvetica,Arial, Lucida Grande, sans-serif; "><small>Tel: +34 915 792 538</small></td><td width="50" align="center" bgcolor="#FFFFFF"/></tr><tr><td width="50" align="center" bgcolor="#FFFFFF"/><td width="400" align="center" bgcolor="#FFFFFF" style="color: #AEAEAE; font-size: 12px;line-height: 1.2; font-family: Helvetica,Arial, Lucida Grande, sans-serif; "><small>© 2017 Fórmula VR</small></td><td width="50" align="center" bgcolor="#FFFFFF"/></tr></table></td></tr></table></td></tr></table><!-- /FOOTER --></body></html>');
INSERT INTO "T_PA_systemParameters" VALUES ('1705091000999', ' ', ' ', 'SYS', 'SYS', 'MAIL_CHANGE_PASSWORD', '<?xml version="1.0" encoding="UTF-8" ?><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"><html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /><title>Registro nuevo usuario</title><style type="text/css">/* CLIENT-SPECIFIC STYLES */	#outlook a {	padding: 0;	}	/* Force Outlook to provide a "view in browser" message */	.ReadMsgBody {	width: 100%;	}		.ExternalClass {	width: 100%;	}	/* Force Hotmail to display emails at full width */	.ExternalClass, .ExternalClass p, .ExternalClass span, .ExternalClass font,	.ExternalClass td, .ExternalClass div {	line-height: 100%;	}	/* Force Hotmail to display normal line spacing */	body, table, td, a {	-webkit-text-size-adjust: 100%;	-ms-text-size-adjust: 100%;	}	/* Prevent WebKit and Windows mobile changing default text sizes */	table, td {	mso-table-lspace: 0pt;	mso-table-rspace: 0pt;	}	/* Remove spacing between tables in Outlook 2007 and up */	img {	-ms-interpolation-mode: bicubic;	}	/* Allow smoother rendering of resized image in Internet Explorer */	/* RESET STYLES */	body {	margin: 0;	padding: 0;	}		img {	border: 0;	height: auto;	line-height: 100%;	outline: none;	text-decoration: none;	}		table {	border-collapse: collapse !important;	}		a {	color: #999999;	text-decoration: underline;	}		body {	height: 100% !important;	margin: 0;	padding: 0;	width: 100% !important;	}	/* iOS BLUE LINKS */	.appleBody a {	color: #666666;	text-decoration: none;	}		.appleFooter a {	color: #999999;	text-decoration: none;	}	/* MOBILE STYLES */	@media screen and (max-width: 525px) {	/* ALLOWS FOR FLUID TABLES */	table[class="wrapper"] {	width: 100% !important;	}	/* ADJUSTS LAYOUT OF LOGO IMAGE */	td[class="logo"] {	text-align: left;	padding: 20px 0 20px 0 !important;	}	td[class="logo"] img {	margin: 0 auto !important;	}	/* USE THESE CLASSES TO HIDE CONTENT ON MOBILE */	td[class="mobile-hide"] {	display: none;	}	img[class="mobile-hide"] {	display: none !important;	}	img[class="img-max"] {	max-width: 100% !important;	height: auto !important;	}	/* FULL-WIDTH TABLES */	table[class="responsive-table"] {	width: 100% !important;	}	/* UTILITY CLASSES FOR ADJUSTING PADDING ON MOBILE */	td[class="padding"] {	padding: 10px 5% 15px 5% !important;	}	td[class="padding-copy"] {	padding: 15px 5% 0px 5% !important;	text-align: center;	}	td[class="padding-meta"] {	padding: 30px 5% 0px 5% !important;	text-align: center;	}	td[class="no-pad"] {	padding: 0 0 20px 0 !important;	}	td[class="no-padding"] {	padding: 0 !important;	}	td[class="section-padding"] {	padding: 10px 10px 10px 10px !important;	}	td[class="section-header"] {	padding: 10px 15px 10px 15px !important;	}	td[class="section-padding-bottom-image"] {	padding: 50px 15px 0 15px !important;	}	/* ADJUST BUTTONS ON MOBILE */	td[class="mobile-wrapper"] {	padding: 10px 5% 15px 5% !important;	}	table[class="mobile-button-container"] {	margin: 0 auto;	width: 100% !important;	}	a[class="mobile-button"] {	width: 80% !important;	padding: 15px !important;	border: 0 !important;	font-size: 16px !important;	}	}</style></head><body><!-- HEADER --><table  width="100%" align="center" bgcolor="#FFFFFF" cellspacing="0" cellpading="0" border="0" ><tr><td width="100%" align="center" bgcolor="#FFFFFF"><table  width="500" align="center" bgcolor="#FFFFFF" cellspacing="0" cellpading="0" border="0" ><!-- espacio 10 --><tr><td width="500" height="10" align="center" bgcolor="#FFFFFF"/></tr><!-- fin espacio --><tr><td width="500" align="center"  bgcolor="#FFFFFF"><table class="table" width="500" align="center" bgcolor="#FFFFFF" cellspacing="0" cellpading="0" border="0" ><tr><td width="187" align="center" bgcolor="#FFFFFF"/><td width="126" align="center" bgcolor="#FFFFFF"><img src="X=X=LOGO_IMG_SRC=X=X" alt="Fórmula VR" width="126" style="display:block;border:none;border.style:none;"/></td><td width="187" align="center" bgcolor="#FFFFFF"/></tr></table></td></tr><tr><td width="100%" align="center" bgcolor="#FFFFFF" style="font-family: Helvetica,Arial, Lucida Grande, sans-serif; font-size: 12px; color: #263238;line-height: 1.2;">Compite en un entorno virtual completamente inmersivo.</td></tr><!-- espacio 10 --><tr><td width="100%" height="10" align="center" bgcolor="#FFFFFF"/></tr><!-- fin espacio --></table></td></tr></table><!-- /HEADER --><!-- ONE COLUMN SECTION --><table border="0" cellpadding="0" cellspacing="0" width="100%"><tr><td bgcolor="#ff0000" align="center" style="padding: 10px 0 10px 0; font-family: Helvetica, Arial, sans-serif; font-size:26px; max-height: 0px; max-width: 0px; overflow: hidden; color:#FFFFFF;" class="section-padding">Cambiar contraseña de usuario Fórmula VR</td></tr></table><!-- BODY --><table  width="100%" align="center" bgcolor="#FFFFFF" cellspacing="0" cellpading="0" border="0" ><tr><td width="100%" align="center" bgcolor="#FFFFFF"><table  width="500" align="center" bgcolor="#FFFFFF" cellspacing="0" cellpading="0" border="0" ><!-- espacio 20 --><tr><td width="500" height="20" align="center" bgcolor="#FFFFFF"/></tr><!-- fin espacio --><!-- content text left 16px--><tr><td width="500" align="left"  bgcolor="#FFFFFF" style="font-family: Helvetica,Arial, Lucida Grande, sans-serif; font-size: 16px; color: #263238;line-height: 1.4;"><p style="font-family: Helvetica,Arial, Lucida Grande, sans-serif;color: #263238;font-size:16px;line-height: 1.4;margin: 0px;">Saludos del equipo Fórmula VR:</p></td></tr><!-- fin content text left --><!-- espacio 20 --><tr><td width="500" height="20" align="center" bgcolor="#FFFFFF"/></tr><!-- fin espacio --><!-- content text center 16px--><tr><td width="500" align="center"  bgcolor="#FFFFFF" style="font-family: Helvetica,Arial, Lucida Grande, sans-serif; font-size: 16px; color: #263238;line-height: 1.4;"><p style="font-family: Helvetica,Arial, Lucida Grande, sans-serif;color: #263238;font-size:16px;line-height: 1.4;margin: 0px;">Para continuar con la operación de cambio de contraseña que has solicitado en Fórmula VR, debes pulsar el siguiente enlace que te permitirá realizarlo de forma segura en nuestra plataforma:</p></td></tr><!-- fin content text center --><!-- espacio 40 --><tr><td width="500" height="20" align="center" bgcolor="#FFFFFF"/></tr><!-- fin espacio --><!-- btn --><tr><td width="500" height="44px" align="center" style="font-family: Helvetica,Arial, Lucida Grande, sans-serif; font-size: 16px; color: #263238;line-height: 1.2;"><a href="X=X=CALLBACK=X=X" target="_blank" style="font-size: 16px; font-family: Helvetica, Arial, sans-serif; font-weight: normal; color: #ffffff; text-decoration: none; background-color: #ff0000 ; border-top: 15px solid #ff0000 ; border-bottom: 15px solid #ff0000 ; border-left: 25px solid #ff0000 ; border-right: 25px solid #ff0000 ; border-radius: 3px; -webkit-border-radius: 3px; -moz-border-radius: 3px; display: inline-block;" class="mobile-button"><b>X=X=TEXTO_BOTON=X=X</b></a></td></tr><!-- espacio 40 --><tr><td width="500" height="20" align="center" bgcolor="#FFFFFF"/></tr><!-- fin espacio --><!-- content text left 16px--><tr><td width="500" align="left"  bgcolor="#FFFFFF" style="font-family: Helvetica,Arial, Lucida Grande, sans-serif; font-size: 16px; color: #263238;line-height: 1.4;"><p style="font-family: Helvetica,Arial, Lucida Grande, sans-serif;color: #263238;font-size:16px;line-height: 1.4;margin: 0px;">Fórmula VR es un nuevo sistema de experiencias virtuales que utiliza lo más avanzado en tecnología de simulación inmersiva.</p></td></tr><!-- fin content text center --><!-- espacio 80 --><tr><td width="500" height="40" align="center" bgcolor="#FFFFFF"/></tr><!-- fin espacio --></table></td></tr></table><!-- /BODY  --><!-- FOOTER --><table  width="100%" align="center" bgcolor="#FFFFFF" cellspacing="0" cellpading="0" border="0" ><tr><td width="100%" align="center" bgcolor="#FFFFFF"><table  width="500" align="center" bgcolor="#FFFFFF" cellspacing="0" cellpading="0" border="0"><!-- espacio 20 --><tr><td width="500" height="20" align="center" bgcolor="#FFFFFF"/></tr><!-- fin espacio --><!-- Informacion footer --><tr><td width="500" align="center" bgcolor="#FFFFFF" style="color: #AEAEAE; font-size: 12px;line-height: 1.4; font-family: Helvetica,Arial, Lucida Grande, sans-serif; "><p style="font-family: Helvetica,Arial, Lucida Grande, sans-serif;color: #AEAEAE;font-size:12px;line-height: 1.4;margin: 0px;">Y recuerda que, si tienes alguna duda o sugerencia sobre Fórmula VR, puedes ponerte en contacto con nosotros en <a href="mailto:info@formulavr.net?Subject=Contacto" target="_top" style="color:#999999;">info@formulavr.net</a>, en nuestra p&aacute;gina web <a href="https://www.formulavr.net" target="_top" style="color:#999999;">www.formulavr.net</a> a trav&eacute;s del chat o en nuestros tel&eacute;fonos de Atenci&oacute;n al Cliente 915792538 &oacute; 655284629. &#161;Estaremos encantados de ayudarte&#33;</p></td></tr><!-- fin Informacion footer --><!-- espacio 10 --><tr><td width="500" height="10" align="center" bgcolor="#FFFFFF"/></tr><!-- fin espacio --><tr><td width="500" align="center" bgcolor="#FFFFFF"><table  width="500" align="center" bgcolor="#FFFFFF" cellspacing="0" cellpading="0" border="0" ><tr><td width="50" align="center" bgcolor="#FFFFFF"/><td width="400" align="center" bgcolor="#FFFFFF" style="color: #AEAEAE; font-size: 12px;line-height: 1.2; font-family: Helvetica,Arial, Lucida Grande, sans-serif; "><small>Fórmula VR. Paseo de las Acacias 21. Coslada, 28821 Spain </small></td><td width="50" align="center" bgcolor="#FFFFFF"/></tr><tr><td width="50" align="center" bgcolor="#FFFFFF"/><td width="400" align="center" bgcolor="#FFFFFF" style="color: #AEAEAE; font-size: 12px;line-height: 1.2; font-family: Helvetica,Arial, Lucida Grande, sans-serif; "><small>Tel: +34 915 792 538</small></td><td width="50" align="center" bgcolor="#FFFFFF"/></tr><tr><td width="50" align="center" bgcolor="#FFFFFF"/><td width="400" align="center" bgcolor="#FFFFFF" style="color: #AEAEAE; font-size: 12px;line-height: 1.2; font-family: Helvetica,Arial, Lucida Grande, sans-serif; "><small>© 2017 Fórmula VR</small></td><td width="50" align="center" bgcolor="#FFFFFF"/></tr></table></td></tr></table></td></tr></table><!-- /FOOTER --></body></html>');
INSERT INTO "T_PA_systemParameters" VALUES ('1705091000999', ' ', ' ', 'SYS', 'SYS', 'MAIL_RESERVE_CONFIRM', '<?xml version="1.0" encoding="UTF-8" ?><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"><html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /><title>Registro nuevo usuario</title><style type="text/css">/* CLIENT-SPECIFIC STYLES */	#outlook a {	padding: 0;	}	/* Force Outlook to provide a "view in browser" message */	.ReadMsgBody {	width: 100%;	}		.ExternalClass {	width: 100%;	}	/* Force Hotmail to display emails at full width */	.ExternalClass, .ExternalClass p, .ExternalClass span, .ExternalClass font,	.ExternalClass td, .ExternalClass div {	line-height: 100%;	}	/* Force Hotmail to display normal line spacing */	body, table, td, a {	-webkit-text-size-adjust: 100%;	-ms-text-size-adjust: 100%;	}	/* Prevent WebKit and Windows mobile changing default text sizes */	table, td {	mso-table-lspace: 0pt;	mso-table-rspace: 0pt;	}	/* Remove spacing between tables in Outlook 2007 and up */	img {	-ms-interpolation-mode: bicubic;	}	/* Allow smoother rendering of resized image in Internet Explorer */	/* RESET STYLES */	body {	margin: 0;	padding: 0;	}		img {	border: 0;	height: auto;	line-height: 100%;	outline: none;	text-decoration: none;	}		table {	border-collapse: collapse !important;	}		a {	color: #999999;	text-decoration: underline;	}		body {	height: 100% !important;	margin: 0;	padding: 0;	width: 100% !important;	}	/* iOS BLUE LINKS */	.appleBody a {	color: #666666;	text-decoration: none;	}		.appleFooter a {	color: #999999;	text-decoration: none;	}	/* MOBILE STYLES */	@media screen and (max-width: 525px) {	/* ALLOWS FOR FLUID TABLES */	table[class="wrapper"] {	width: 100% !important;	}	/* ADJUSTS LAYOUT OF LOGO IMAGE */	td[class="logo"] {	text-align: left;	padding: 20px 0 20px 0 !important;	}	td[class="logo"] img {	margin: 0 auto !important;	}	/* USE THESE CLASSES TO HIDE CONTENT ON MOBILE */	td[class="mobile-hide"] {	display: none;	}	img[class="mobile-hide"] {	display: none !important;	}	img[class="img-max"] {	max-width: 100% !important;	height: auto !important;	}	/* FULL-WIDTH TABLES */	table[class="responsive-table"] {	width: 100% !important;	}	/* UTILITY CLASSES FOR ADJUSTING PADDING ON MOBILE */	td[class="padding"] {	padding: 10px 5% 15px 5% !important;	}	td[class="padding-copy"] {	padding: 15px 5% 0px 5% !important;	text-align: center;	}	td[class="padding-meta"] {	padding: 30px 5% 0px 5% !important;	text-align: center;	}	td[class="no-pad"] {	padding: 0 0 20px 0 !important;	}	td[class="no-padding"] {	padding: 0 !important;	}	td[class="section-padding"] {	padding: 10px 10px 10px 10px !important;	}	td[class="section-header"] {	padding: 10px 15px 10px 15px !important;	}	td[class="section-padding-bottom-image"] {	padding: 50px 15px 0 15px !important;	}	/* ADJUST BUTTONS ON MOBILE */	td[class="mobile-wrapper"] {	padding: 10px 5% 15px 5% !important;	}	table[class="mobile-button-container"] {	margin: 0 auto;	width: 100% !important;	}	a[class="mobile-button"] {	width: 80% !important;	padding: 15px !important;	border: 0 !important;	font-size: 16px !important;	}	}</style></head><body><!-- HEADER --><table  width="100%" align="center" bgcolor="#FFFFFF" cellspacing="0" cellpading="0" border="0" ><tr><td width="100%" align="center" bgcolor="#FFFFFF"><table  width="500" align="center" bgcolor="#FFFFFF" cellspacing="0" cellpading="0" border="0" ><!-- espacio 10 --><tr><td width="500" height="10" align="center" bgcolor="#FFFFFF"/></tr><!-- fin espacio --><tr><td width="500" align="center"  bgcolor="#FFFFFF"><table class="table" width="500" align="center" bgcolor="#FFFFFF" cellspacing="0" cellpading="0" border="0" ><tr><td width="187" align="center" bgcolor="#FFFFFF"/><td width="126" align="center" bgcolor="#FFFFFF"><img src="X=X=LOGO_IMG_SRC=X=X" alt="Fórmula VR" width="126" style="display:block;border:none;border.style:none;"/></td><td width="187" align="center" bgcolor="#FFFFFF"/></tr></table></td></tr><tr><td width="100%" align="center" bgcolor="#FFFFFF" style="font-family: Helvetica,Arial, Lucida Grande, sans-serif; font-size: 12px; color: #263238;line-height: 1.2;">Compite en un entorno virtual completamente inmersivo.</td></tr><!-- espacio 10 --><tr><td width="100%" height="10" align="center" bgcolor="#FFFFFF"/></tr><!-- fin espacio --></table></td></tr></table><!-- /HEADER --><!-- ONE COLUMN SECTION --><table border="0" cellpadding="0" cellspacing="0" width="100%"><tr><td bgcolor="#ff0000" align="center" style="padding: 10px 0 10px 0; font-family: Helvetica, Arial, sans-serif; font-size:26px; max-height: 0px; max-width: 0px; overflow: hidden; color:#FFFFFF;" class="section-padding">Reserva de usuario para Fórmula VR</td></tr></table><!-- BODY --><table  width="100%" align="center" bgcolor="#FFFFFF" cellspacing="0" cellpading="0" border="0" ><tr><td width="100%" align="center" bgcolor="#FFFFFF"><table  width="500" align="center" bgcolor="#FFFFFF" cellspacing="0" cellpading="0" border="0" ><!-- espacio 20 --><tr><td width="500" height="20" align="center" bgcolor="#FFFFFF"/></tr><!-- fin espacio --><!-- content text left 16px--><tr><td width="500" align="left"  bgcolor="#FFFFFF" style="font-family: Helvetica,Arial, Lucida Grande, sans-serif; font-size: 16px; color: #263238;line-height: 1.4;"><p style="font-family: Helvetica,Arial, Lucida Grande, sans-serif;color: #263238;font-size:16px;line-height: 1.4;margin: 0px;">Saludos del equipo Fórmula VR:</p></td></tr><!-- fin content text left --><!-- espacio 20 --><tr><td width="500" height="20" align="center" bgcolor="#FFFFFF"/></tr><!-- fin espacio --><!-- content text center 16px--><tr><td width="500" align="center"  bgcolor="#FFFFFF" style="font-family: Helvetica,Arial, Lucida Grande, sans-serif; font-size: 16px; color: #263238;line-height: 1.4;"><p style="font-family: Helvetica,Arial, Lucida Grande, sans-serif;color: #263238;font-size:16px;line-height: 1.4;margin: 0px;">Este es el código de tu reserva:</p></td></tr><!-- fin content text center --><!-- espacio 40 --><tr><td width="500" height="20" align="center" bgcolor="#FFFFFF"/></tr><!-- fin espacio --><!-- btn --><tr><td width="500" height="44px" align="center" style="font-family: Helvetica,Arial, Lucida Grande, sans-serif; font-size: 16px; color: #263238;line-height: 1.2;"><a href="X=X=CALLBACK=X=X" target="_blank" style="font-size: 16px; font-family: Helvetica, Arial, sans-serif; font-weight: normal; color: #ffffff; text-decoration: none; background-color: #ff0000 ; border-top: 15px solid #ff0000 ; border-bottom: 15px solid #ff0000 ; border-left: 25px solid #ff0000 ; border-right: 25px solid #ff0000 ; border-radius: 3px; -webkit-border-radius: 3px; -moz-border-radius: 3px; display: inline-block;" class="mobile-button"><b>X=X=TEXTO_BOTON=X=X</b></a></td></tr><!-- espacio 40 --><tr><td width="500" height="20" align="center" bgcolor="#FFFFFF"/></tr><!-- fin espacio --><!-- content text left 16px--><tr><td width="500" align="left"  bgcolor="#FFFFFF" style="font-family: Helvetica,Arial, Lucida Grande, sans-serif; font-size: 16px; color: #263238;line-height: 1.4;"><p style="font-family: Helvetica,Arial, Lucida Grande, sans-serif;color: #263238;font-size:16px;line-height: 1.4;margin: 0px;">Fórmula VR es un nuevo sistema de experiencias virtuales que utiliza lo más avanzado en tecnología de simulación inmersiva.</p></td></tr><!-- fin content text center --><!-- espacio 80 --><tr><td width="500" height="40" align="center" bgcolor="#FFFFFF"/></tr><!-- fin espacio --></table></td></tr></table><!-- /BODY  --><!-- FOOTER --><table  width="100%" align="center" bgcolor="#FFFFFF" cellspacing="0" cellpading="0" border="0" ><tr><td width="100%" align="center" bgcolor="#FFFFFF"><table  width="500" align="center" bgcolor="#FFFFFF" cellspacing="0" cellpading="0" border="0"><!-- espacio 20 --><tr><td width="500" height="20" align="center" bgcolor="#FFFFFF"/></tr><!-- fin espacio --><!-- Informacion footer --><tr><td width="500" align="center" bgcolor="#FFFFFF" style="color: #AEAEAE; font-size: 12px;line-height: 1.4; font-family: Helvetica,Arial, Lucida Grande, sans-serif; "><p style="font-family: Helvetica,Arial, Lucida Grande, sans-serif;color: #AEAEAE;font-size:12px;line-height: 1.4;margin: 0px;">Y recuerda que, si tienes alguna duda o sugerencia sobre Fórmula VR, puedes ponerte en contacto con nosotros en <a href="mailto:info@formulavr.net?Subject=Contacto" target="_top" style="color:#999999;">info@formulavr.net</a>, en nuestra p&aacute;gina web <a href="https://www.formulavr.net" target="_top" style="color:#999999;">www.formulavr.net</a> a trav&eacute;s del chat o en nuestros tel&eacute;fonos de Atenci&oacute;n al Cliente 915792538 &oacute; 655284629. &#161;Estaremos encantados de ayudarte&#33;</p></td></tr><!-- fin Informacion footer --><!-- espacio 10 --><tr><td width="500" height="10" align="center" bgcolor="#FFFFFF"/></tr><!-- fin espacio --><tr><td width="500" align="center" bgcolor="#FFFFFF"><table  width="500" align="center" bgcolor="#FFFFFF" cellspacing="0" cellpading="0" border="0" ><tr><td width="50" align="center" bgcolor="#FFFFFF"/><td width="400" align="center" bgcolor="#FFFFFF" style="color: #AEAEAE; font-size: 12px;line-height: 1.2; font-family: Helvetica,Arial, Lucida Grande, sans-serif; "><small>Fórmula VR. Paseo de las Acacias 21. Coslada, 28821 Spain </small></td><td width="50" align="center" bgcolor="#FFFFFF"/></tr><tr><td width="50" align="center" bgcolor="#FFFFFF"/><td width="400" align="center" bgcolor="#FFFFFF" style="color: #AEAEAE; font-size: 12px;line-height: 1.2; font-family: Helvetica,Arial, Lucida Grande, sans-serif; "><small>Tel: +34 915 792 538</small></td><td width="50" align="center" bgcolor="#FFFFFF"/></tr><tr><td width="50" align="center" bgcolor="#FFFFFF"/><td width="400" align="center" bgcolor="#FFFFFF" style="color: #AEAEAE; font-size: 12px;line-height: 1.2; font-family: Helvetica,Arial, Lucida Grande, sans-serif; "><small>© 2017 Fórmula VR</small></td><td width="50" align="center" bgcolor="#FFFFFF"/></tr></table></td></tr></table></td></tr></table><!-- /FOOTER --></body></html>');
INSERT INTO "T_PA_systemParameters" VALUES ('1705091000999', ' ', ' ', 'SYS', 'SYS', 'TPV_LaCaixa_Ds_Merchant_MerchantCode', '343583191');
INSERT INTO "T_PA_systemParameters" VALUES ('1705091000999', ' ', ' ', 'SYS', 'SYS', 'TPV_LaCaixa_Ds_Merchant_Terminal', '1');
INSERT INTO "T_PA_systemParameters" VALUES ('1705091000999', ' ', ' ', 'SYS', 'SYS', 'TPV_LaCaixa_Ds_Merchant_Currency', '978');
INSERT INTO "T_PA_systemParameters" VALUES ('1705091000999', ' ', ' ', 'SYS', 'SYS', 'TPV_LaCaixa_claveCifrado', 'sq7HjrUOBfKmC576ILgskD5srU870gJ7');
INSERT INTO "T_PA_systemParameters" VALUES ('1705091000999', ' ', ' ', 'SYS', 'SYS', 'TPV_LaCaixa_tipoCifrado', 'SHA256');
INSERT INTO "T_PA_systemParameters" VALUES ('1705091000999', ' ', ' ', 'SYS', 'SYS', 'TPV_LaCaixa_version', 'HMAC_SHA256_V1');
INSERT INTO "T_PA_systemParameters" VALUES ('1705091000999', ' ', ' ', 'SYS', 'SYS', 'TPV_LaCaixa_URL', 'https://sis-t.redsys.es:25443/sis/realizarPago');
INSERT INTO "T_PA_systemParameters" VALUES ('1705091000999', ' ', ' ', 'SYS', 'CENTRAL', 'Paypal_API_Checkout_Express_URL', 'https://api-3t.sandbox.paypal.com/nvp');
INSERT INTO "T_PA_systemParameters" VALUES ('1705091000999', ' ', ' ', 'SYS', 'CENTRAL', 'Paypal_API_Checkout_Express_Version', '204');
INSERT INTO "T_PA_systemParameters" VALUES ('1705091000999', ' ', ' ', 'SYS', 'CENTRAL', 'Paypal_Redirect_Checkout_Express_URL', 'https://www.sandbox.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token=');
INSERT INTO "T_PA_systemParameters" VALUES ('1705091000999', ' ', ' ', 'SYS', 'CENTRAL', 'Paypal_MERCHANT_URL_REG', 'https://www.sandbox.paypal.com/es/merchantsignup/applicationChecklist?signupType=CREATE_NEW_ACCOUNT&productIntentId=wp_standard');
INSERT INTO "T_PA_systemParameters" VALUES ('1705091000999', ' ', ' ', 'SYS', 'CENTRAL', 'Paypal_MERCHANT_URL_ACCESS', 'https://www.sandbox.paypal.com/signin?country.x=ES&locale.x=es_ES');
INSERT INTO "T_PA_systemParameters" VALUES ('1705091000999', ' ', ' ', 'SYS', 'CENTRAL', 'Paypal_IPN_URL', 'https://www.sandbox.paypal.com/cgi-bin/webscr');
INSERT INTO "T_PA_systemParameters" VALUES ('1705091000999', ' ', ' ', 'SYS', 'CENTRAL', 'Paypal_Callback_Checkout_Express_URL', 'http://localhost:8080/FormulaVR/Paypal');
INSERT INTO "T_PA_systemParameters" VALUES ('1705091000999', ' ', ' ', 'SYS', 'CENTRAL', 'Paypal_MERCHANT_FVR_USR', 'dietadelbocadillo-facilitator_api1.outlook.es');
INSERT INTO "T_PA_systemParameters" VALUES ('1705091000999', ' ', ' ', 'SYS', 'CENTRAL', 'Paypal_MERCHANT_FVR_PWD', 'DQKQNWSDPREQDPN9');
INSERT INTO "T_PA_systemParameters" VALUES ('1705091000999', ' ', ' ', 'SYS', 'CENTRAL', 'Paypal_MERCHANT_FVR_SIGNATURE', 'AFcWxV21C7fd0v3bYYYRCpSSRl31A2bxjVYW0oLhOZpG5xAdAXqm7ab5');
INSERT INTO "T_PA_systemParameters" VALUES ('1705091000999', ' ', ' ', 'SYS', 'SYS', 'RS_LAST_KEY', '172480005');


-- Completed on 2017-09-05 10:22:43

--
-- PostgreSQL database dump complete
--

