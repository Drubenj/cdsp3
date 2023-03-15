package com.mysena.cdsp3.app.utilidades.reportes;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.mysena.cdsp3.app.entities.Venta;

public class VentaExporterPDF {

	private List<Venta> listaVentas;

	public VentaExporterPDF(List<Venta> listaVentas) {
		super();
		this.listaVentas = listaVentas;
	}

	private void escribirCabeceraTabla(PdfPTable tabla) {
		PdfPCell celda = new PdfPCell();

		celda.setBackgroundColor(Color.GRAY);
		celda.setPadding(5);

		Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
		fuente.setColor(Color.WHITE);

		celda.setPhrase(new Phrase("Id", fuente));
		tabla.addCell(celda);

		celda.setPhrase(new Phrase("Usuario nombre", fuente));
		tabla.addCell(celda);
		
		celda.setPhrase(new Phrase("Usuario apellido", fuente));
		tabla.addCell(celda);

		celda.setPhrase(new Phrase("Pedido", fuente));
		tabla.addCell(celda);

		celda.setPhrase(new Phrase("Fecha", fuente));
		tabla.addCell(celda);

		celda.setPhrase(new Phrase("Total", fuente));
		tabla.addCell(celda);
	}

	private void escribirDatosTabla(PdfPTable tabla) {
		for (Venta venta : listaVentas) {
			tabla.addCell(String.valueOf(venta.getId()));
			tabla.addCell(venta.getUsuario().getNombres());
			tabla.addCell(venta.getUsuario().getApellidos());
			tabla.addCell(String.valueOf(venta.getPedido().getId()));
			tabla.addCell(venta.getFecha().toString());
			tabla.addCell(String.valueOf(venta.getTotal()));
		}

	}

	public void exportar(HttpServletResponse response) throws DocumentException, IOException {
		Document documento = new Document(PageSize.A4);
		PdfWriter.getInstance(documento, response.getOutputStream());

		documento.open();

		Font fuente = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		fuente.setColor(Color.RED);
		fuente.setSize(18);

		Paragraph titulo = new Paragraph("Reporte de Ventas", fuente);

		titulo.setAlignment(Paragraph.ALIGN_CENTER);
		documento.add(titulo);
		PdfPTable tabla = new PdfPTable(6);
		tabla.setWidthPercentage(100);
		tabla.setSpacingBefore(15);
		tabla.setWidths(new float[] { 1f, 2.3f, 2.3f, 1f, 2.9f, 2.1f});
		tabla.setWidthPercentage(110);

		escribirCabeceraTabla(tabla);
		escribirDatosTabla(tabla);

		documento.add(tabla);
		documento.close();
	}
}
