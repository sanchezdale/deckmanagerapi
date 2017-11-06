-- MySQL dump 10.13  Distrib 5.7.17, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: test_db
-- ------------------------------------------------------
-- Server version	5.7.19-0ubuntu0.16.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `client_details`
--

LOCK TABLES `client_details` WRITE;
/*!40000 ALTER TABLE `client_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `client_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `email_template`
--

LOCK TABLES `email_template` WRITE;
/*!40000 ALTER TABLE `email_template` DISABLE KEYS */;
INSERT INTO `email_template` VALUES (1,'<!DOCTYPE html>\n<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">\n<head>\n    <meta charset=\"utf-8\"> <!-- utf-8 works for most cases -->\n    <meta name=\"viewport\" content=\"width=device-width\"> <!-- Forcing initial-scale shouldn\'t be necessary -->\n    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"> <!-- Use the latest (edge) version of IE rendering engine -->\n    <meta name=\"x-apple-disable-message-reformatting\">  <!-- Disable auto-scale in iOS 10 Mail entirely -->\n    <title>Your activation link - Wizards of Coding</title> <!-- The title tag shows in email notifications, like Android 4.4. -->\n    <style>\n\n        /* What it does: Remove spaces around the email design added by some email clients. */\n        /* Beware: It can remove the padding / margin and add a background color to the compose a reply window. */\n        html,\n        body {\n            margin: 0 auto !important;\n            padding: 0 !important;\n            height: 100% !important;\n            width: 100% !important;\n        }\n\n        /* What it does: Stops email clients resizing small text. */\n        * {\n            -ms-text-size-adjust: 100%;\n            -webkit-text-size-adjust: 100%;\n        }\n\n        /* What it does: Centers email on Android 4.4 */\n        div[style*=\"margin: 16px 0\"] {\n            margin: 0 !important;\n        }\n\n        /* What it does: Stops Outlook from adding extra spacing to tables. */\n        table,\n        td {\n            mso-table-lspace: 0pt !important;\n            mso-table-rspace: 0pt !important;\n        }\n\n        /* What it does: Fixes webkit padding issue. Fix for Yahoo mail table alignment bug. Applies table-layout to the first 2 tables then removes for anything nested deeper. */\n        table {\n            border-spacing: 0 !important;\n            border-collapse: collapse !important;\n            table-layout: fixed !important;\n            margin: 0 auto !important;\n        }\n        table table table {\n            table-layout: auto;\n        }\n\n        /* What it does: Uses a better rendering method when resizing images in IE. */\n        img {\n            -ms-interpolation-mode:bicubic;\n        }\n\n        /* What it does: A work-around for email clients meddling in triggered links. */\n        *[x-apple-data-detectors],  /* iOS */\n        .x-gmail-data-detectors,    /* Gmail */\n        .x-gmail-data-detectors *,\n        .aBn {\n            border-bottom: 0 !important;\n            cursor: default !important;\n            color: inherit !important;\n            text-decoration: none !important;\n            font-size: inherit !important;\n            font-family: inherit !important;\n            font-weight: inherit !important;\n            line-height: inherit !important;\n        }\n\n        /* What it does: Prevents Gmail from displaying an download button on large, non-linked images. */\n        .a6S {\n           display: none !important;\n           opacity: 0.01 !important;\n       }\n       /* If the above doesn\'t work, add a .g-img class to any image in question. */\n       img.g-img + div {\n           display: none !important;\n       }\n\n       /* What it does: Prevents underlining the button text in Windows 10 */\n        .button-link {\n            text-decoration: none !important;\n        }\n\n        /* What it does: Removes right gutter in Gmail iOS app: https://github.com/TedGoas/Cerberus/issues/89  */\n        /* Create one of these media queries for each additional viewport size you\'d like to fix */\n        /* Thanks to Eric Lepetit (@ericlepetitsf) for help troubleshooting */\n        @media only screen and (min-device-width: 375px) and (max-device-width: 413px) { /* iPhone 6 and 6+ */\n            .email-container {\n                min-width: 375px !important;\n            }\n        }\n\n    </style>\n    <!-- CSS Reset : END -->\n\n    <!-- Progressive Enhancements : BEGIN -->\n    <style>\n\n        /* What it does: Hover styles for buttons */\n        .button-td,\n        .button-a {\n            transition: all 100ms ease-in;\n        }\n        .button-td:hover,\n        .button-a:hover {\n            background: #555555 !important;\n            border-color: #555555 !important;\n        }\n\n        /* Media Queries */\n        @media screen and (max-width: 600px) {\n\n            .email-container {\n                width: 100% !important;\n                margin: auto !important;\n            }\n\n            /* What it does: Forces elements to resize to the full width of their container. Useful for resizing images beyond their max-width. */\n            .fluid {\n                max-width: 100% !important;\n                height: auto !important;\n                margin-left: auto !important;\n                margin-right: auto !important;\n            }\n\n            /* What it does: Forces table cells into full-width rows. */\n            .stack-column,\n            .stack-column-center {\n                display: block !important;\n                width: 100% !important;\n                max-width: 100% !important;\n                direction: ltr !important;\n            }\n            /* And center justify these ones. */\n            .stack-column-center {\n                text-align: center !important;\n            }\n\n            /* What it does: Generic utility class for centering. Useful for images, buttons, and nested tables. */\n            .center-on-narrow {\n                text-align: center !important;\n                display: block !important;\n                margin-left: auto !important;\n                margin-right: auto !important;\n                float: none !important;\n            }\n            table.center-on-narrow {\n                display: inline-block !important;\n            }\n\n            /* What it does: Adjust typography on small screens to improve readability */\n            .email-container p {\n                font-size: 17px !important;\n                line-height: 22px !important;\n            }\n        }\n\n    </style>\n    <!-- Progressive Enhancements : END -->\n\n    <!-- What it does: Makes background images in 72ppi Outlook render at correct size. -->\n    <!--[if gte mso 9]>\n    <xml>\n        <o:OfficeDocumentSettings>\n            <o:AllowPNG/>\n            <o:PixelsPerInch>96</o:PixelsPerInch>\n        </o:OfficeDocumentSettings>\n    </xml>\n    <![endif]-->\n\n</head>\n<body width=\"100%\" bgcolor=\"#222222\" style=\"margin: 0; mso-line-height-rule: exactly;\">\n    <center style=\"width: 100%; background: #222222; text-align: left;\">\n\n        <!-- Visually Hidden Preheader Text : BEGIN -->\n        <div style=\"display: none; font-size: 1px; line-height: 1px; max-height: 0px; max-width: 0px; opacity: 0; overflow: hidden; mso-hide: all; font-family: sans-serif;\">\n            (Optional) This text will appear in the inbox preview, but not the email body. It can be used to supplement the email subject line or even summarize the email\'s contents. Extended text preheaders (~490 characters) seems like a better UX for anyone using a screenreader or voice-command apps like Siri to dictate the contents of an email. If this text is not included, email clients will automatically populate it using the text (including image alt text) at the start of the email\'s body.\n        </div>\n        <!-- Visually Hidden Preheader Text : END -->\n\n        <!-- Email Header : BEGIN -->\n        <table role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" align=\"center\" width=\"600\" style=\"margin: auto;\" class=\"email-container\">\n            <tr>\n                <td style=\"padding: 20px 0; text-align: center\">\n                    <img src=\"http://placehold.it/200x50\" width=\"200\" height=\"50\" alt=\"alt_text\" border=\"0\" style=\"height: auto; background: #dddddd; font-family: sans-serif; font-size: 15px; line-height: 20px; color: #555555;\">\n                </td>\n            </tr>\n        </table>\n        <!-- Email Header : END -->\n\n        <!-- Email Body : BEGIN -->\n        <table role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" align=\"center\" width=\"600\" style=\"margin: auto;\" class=\"email-container\">\n\n            <!-- Hero Image, Flush : BEGIN -->\n            <tr>\n                <td bgcolor=\"#ffffff\" align=\"center\">\n                    <img src=\"https://s3.amazonaws.com/wizardsofthecode/header.jpg\" width=\"600\" height=\"\" alt=\"alt_text\" border=\"0\" align=\"center\" style=\"width: 100%; max-width: 600px; height: auto; background: #dddddd; font-family: sans-serif; font-size: 15px; line-height: 20px; color: #555555; margin: auto;\" class=\"g-img\">\n                </td>\n            </tr>\n            <!-- Hero Image, Flush : END -->\n\n            <!-- 1 Column Text + Button : BEGIN -->\n            <tr>\n                <td bgcolor=\"#ffffff\" style=\"padding: 40px 40px 20px; text-align: center;\">\n                    <h1 style=\"margin: 0; font-family: sans-serif; font-size: 24px; line-height: 27px; color: #333333; font-weight: normal;\">Please activate your &nbsp;Account.</h1>\n                </td>\n            </tr>\n            <tr>\n                <td bgcolor=\"#ffffff\" style=\"padding: 0 40px 40px; font-family: sans-serif; font-size: 15px; line-height: 20px; color: #555555; text-align: center;\">\n                    <p style=\"margin: 0;\">Hello [NAME], Follow this link <a href=\"[ACTIVATION_URL]\"> Activate your account</a> to activate your account and enjoy the latest Deck management Experience. If the link does not work, copy and paste this address in your browser: <br /> [ACTIVATION_URL]</p>\n                </td>\n            </tr>\n            <!-- 1 Column Text + Button : END -->\n\n    </table>\n    <!-- Email Body : END -->\n\n    </center>\n</body>\n</html>\n','ACTIVATION_TEMPLATE','Activate your account now!'),(2,'<!DOCTYPE html>\n<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">\n<head>\n    <meta charset=\"utf-8\"> <!-- utf-8 works for most cases -->\n    <meta name=\"viewport\" content=\"width=device-width\"> <!-- Forcing initial-scale shouldn\'t be necessary -->\n    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"> <!-- Use the latest (edge) version of IE rendering engine -->\n    <meta name=\"x-apple-disable-message-reformatting\">  <!-- Disable auto-scale in iOS 10 Mail entirely -->\n    <title>Your activation link - Wizards of Coding</title> <!-- The title tag shows in email notifications, like Android 4.4. -->\n    <style>\n\n        /* What it does: Remove spaces around the email design added by some email clients. */\n        /* Beware: It can remove the padding / margin and add a background color to the compose a reply window. */\n        html,\n        body {\n            margin: 0 auto !important;\n            padding: 0 !important;\n            height: 100% !important;\n            width: 100% !important;\n        }\n\n        /* What it does: Stops email clients resizing small text. */\n        * {\n            -ms-text-size-adjust: 100%;\n            -webkit-text-size-adjust: 100%;\n        }\n\n        /* What it does: Centers email on Android 4.4 */\n        div[style*=\"margin: 16px 0\"] {\n            margin: 0 !important;\n        }\n\n        /* What it does: Stops Outlook from adding extra spacing to tables. */\n        table,\n        td {\n            mso-table-lspace: 0pt !important;\n            mso-table-rspace: 0pt !important;\n        }\n\n        /* What it does: Fixes webkit padding issue. Fix for Yahoo mail table alignment bug. Applies table-layout to the first 2 tables then removes for anything nested deeper. */\n        table {\n            border-spacing: 0 !important;\n            border-collapse: collapse !important;\n            table-layout: fixed !important;\n            margin: 0 auto !important;\n        }\n        table table table {\n            table-layout: auto;\n        }\n\n        /* What it does: Uses a better rendering method when resizing images in IE. */\n        img {\n            -ms-interpolation-mode:bicubic;\n        }\n\n        /* What it does: A work-around for email clients meddling in triggered links. */\n        *[x-apple-data-detectors],  /* iOS */\n        .x-gmail-data-detectors,    /* Gmail */\n        .x-gmail-data-detectors *,\n        .aBn {\n            border-bottom: 0 !important;\n            cursor: default !important;\n            color: inherit !important;\n            text-decoration: none !important;\n            font-size: inherit !important;\n            font-family: inherit !important;\n            font-weight: inherit !important;\n            line-height: inherit !important;\n        }\n\n        /* What it does: Prevents Gmail from displaying an download button on large, non-linked images. */\n        .a6S {\n           display: none !important;\n           opacity: 0.01 !important;\n       }\n       /* If the above doesn\'t work, add a .g-img class to any image in question. */\n       img.g-img + div {\n           display: none !important;\n       }\n\n       /* What it does: Prevents underlining the button text in Windows 10 */\n        .button-link {\n            text-decoration: none !important;\n        }\n\n        /* What it does: Removes right gutter in Gmail iOS app: https://github.com/TedGoas/Cerberus/issues/89  */\n        /* Create one of these media queries for each additional viewport size you\'d like to fix */\n        /* Thanks to Eric Lepetit (@ericlepetitsf) for help troubleshooting */\n        @media only screen and (min-device-width: 375px) and (max-device-width: 413px) { /* iPhone 6 and 6+ */\n            .email-container {\n                min-width: 375px !important;\n            }\n        }\n\n    </style>\n    <!-- CSS Reset : END -->\n\n    <!-- Progressive Enhancements : BEGIN -->\n    <style>\n\n        /* What it does: Hover styles for buttons */\n        .button-td,\n        .button-a {\n            transition: all 100ms ease-in;\n        }\n        .button-td:hover,\n        .button-a:hover {\n            background: #555555 !important;\n            border-color: #555555 !important;\n        }\n\n        /* Media Queries */\n        @media screen and (max-width: 600px) {\n\n            .email-container {\n                width: 100% !important;\n                margin: auto !important;\n            }\n\n            /* What it does: Forces elements to resize to the full width of their container. Useful for resizing images beyond their max-width. */\n            .fluid {\n                max-width: 100% !important;\n                height: auto !important;\n                margin-left: auto !important;\n                margin-right: auto !important;\n            }\n\n            /* What it does: Forces table cells into full-width rows. */\n            .stack-column,\n            .stack-column-center {\n                display: block !important;\n                width: 100% !important;\n                max-width: 100% !important;\n                direction: ltr !important;\n            }\n            /* And center justify these ones. */\n            .stack-column-center {\n                text-align: center !important;\n            }\n\n            /* What it does: Generic utility class for centering. Useful for images, buttons, and nested tables. */\n            .center-on-narrow {\n                text-align: center !important;\n                display: block !important;\n                margin-left: auto !important;\n                margin-right: auto !important;\n                float: none !important;\n            }\n            table.center-on-narrow {\n                display: inline-block !important;\n            }\n\n            /* What it does: Adjust typography on small screens to improve readability */\n            .email-container p {\n                font-size: 17px !important;\n                line-height: 22px !important;\n            }\n        }\n\n    </style>\n    <!-- Progressive Enhancements : END -->\n\n    <!-- What it does: Makes background images in 72ppi Outlook render at correct size. -->\n    <!--[if gte mso 9]>\n    <xml>\n        <o:OfficeDocumentSettings>\n            <o:AllowPNG/>\n            <o:PixelsPerInch>96</o:PixelsPerInch>\n        </o:OfficeDocumentSettings>\n    </xml>\n    <![endif]-->\n\n</head>\n<body width=\"100%\" bgcolor=\"#222222\" style=\"margin: 0; mso-line-height-rule: exactly;\">\n    <center style=\"width: 100%; background: #222222; text-align: left;\">\n\n        <!-- Visually Hidden Preheader Text : BEGIN -->\n        <div style=\"display: none; font-size: 1px; line-height: 1px; max-height: 0px; max-width: 0px; opacity: 0; overflow: hidden; mso-hide: all; font-family: sans-serif;\">\n            (Optional) This text will appear in the inbox preview, but not the email body. It can be used to supplement the email subject line or even summarize the email\'s contents. Extended text preheaders (~490 characters) seems like a better UX for anyone using a screenreader or voice-command apps like Siri to dictate the contents of an email. If this text is not included, email clients will automatically populate it using the text (including image alt text) at the start of the email\'s body.\n        </div>\n        <!-- Visually Hidden Preheader Text : END -->\n\n        <!-- Email Header : BEGIN -->\n        <table role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" align=\"center\" width=\"600\" style=\"margin: auto;\" class=\"email-container\">\n            <tr>\n                <td style=\"padding: 20px 0; text-align: center\">\n                    <img src=\"http://placehold.it/200x50\" width=\"200\" height=\"50\" alt=\"alt_text\" border=\"0\" style=\"height: auto; background: #dddddd; font-family: sans-serif; font-size: 15px; line-height: 20px; color: #555555;\">\n                </td>\n            </tr>\n        </table>\n        <!-- Email Header : END -->\n\n        <!-- Email Body : BEGIN -->\n        <table role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" align=\"center\" width=\"600\" style=\"margin: auto;\" class=\"email-container\">\n\n            <!-- Hero Image, Flush : BEGIN -->\n            <tr>\n                <td bgcolor=\"#ffffff\" align=\"center\">\n                    <img src=\"https://s3.amazonaws.com/wizardsofthecode/header-2.png\" width=\"600\" height=\"\" alt=\"alt_text\" border=\"0\" align=\"center\" style=\"width: 100%; max-width: 600px; height: auto; background: #dddddd; font-family: sans-serif; font-size: 15px; line-height: 20px; color: #555555; margin: auto;\" class=\"g-img\">\n                </td>\n            </tr>\n            <!-- Hero Image, Flush : END -->\n\n            <!-- 1 Column Text + Button : BEGIN -->\n            <tr>\n                <td bgcolor=\"#ffffff\" style=\"padding: 40px 40px 20px; text-align: center;\">\n                    <h1 style=\"margin: 0; font-family: sans-serif; font-size: 24px; line-height: 27px; color: #333333; font-weight: normal;\">Looks like you forgot your&nbsp;Credentials.</h1>\n                </td>\n            </tr>\n            <tr>\n                <td bgcolor=\"#ffffff\" style=\"padding: 0 40px 40px; font-family: sans-serif; font-size: 15px; line-height: 20px; color: #555555; text-align: center;\">\n                    <p style=\"margin: 0;\">No worries [NAME], Follow <a href=\"[CLIENT_URL]\">this link</a> to reset your password and keep enjoying your Deck management Experience. If the link does not work, copy and paste this address in your browser: <br /> [CLIENT_URL]</p>\n                </td>\n            </tr>\n            <!-- 1 Column Text + Button : END -->\n\n    </table>\n    <!-- Email Body : END -->\n\n    </center>\n</body>\n</html>\n','RESET_PASSWORD_TEMPLATE','Reset Your Password');
/*!40000 ALTER TABLE `email_template` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `group_roles`
--

LOCK TABLES `group_roles` WRITE;
/*!40000 ALTER TABLE `group_roles` DISABLE KEYS */;
INSERT INTO `group_roles` VALUES (1,'ROLE_USER'),(2,'ROLE_ADMIN');
/*!40000 ALTER TABLE `group_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `server_settings`
--

LOCK TABLES `server_settings` WRITE;
/*!40000 ALTER TABLE `server_settings` DISABLE KEYS */;
INSERT INTO `server_settings` VALUES (1,'EMAIL_ACTIVE','true'),(2,'SERVER_EMAIL','no-reply@wizardsofcoding.com'),(3,'SERVER_URL','http://api.wizardsofcoding.com/');
/*!40000 ALTER TABLE `server_settings` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-10-16  1:42:43
