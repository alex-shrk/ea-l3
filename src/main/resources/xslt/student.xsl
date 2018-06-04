<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">


        <html>
            <head>
                <title>Students</title>
            </head>
            <body>

                <!--<p>
                    <a href="../DirectorController/director">Режиссеры</a>
                </p>
                <p>
                    <a href="../FilmController/film">Фильмы</a>
                </p>-->

                <table border="1">

                    <tr>
                        <th>id</th>
                        <th>Name</th>
                        <th>Passport number</th>
                    </tr>

                    <xsl:for-each select="List/item">
                        <xsl:sort/>
                        <tr>
                            <td><xsl:value-of select="id"/></td>
                            <td><xsl:value-of select="name"/></td>
                            <td><xsl:value-of select="passportNumber"/></td>
                        </tr>
                    </xsl:for-each>

                </table>
            </body>
        </html>

    </xsl:template>

</xsl:stylesheet>