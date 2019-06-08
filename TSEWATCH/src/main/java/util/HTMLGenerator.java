package util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import com.webfirmframework.wffweb.tag.html.Body;
import com.webfirmframework.wffweb.tag.html.Br;
import com.webfirmframework.wffweb.tag.html.H1;
import com.webfirmframework.wffweb.tag.html.H2;
import com.webfirmframework.wffweb.tag.html.H3;
import com.webfirmframework.wffweb.tag.html.Hr;
import com.webfirmframework.wffweb.tag.html.Html;
import com.webfirmframework.wffweb.tag.html.P;
import com.webfirmframework.wffweb.tag.html.TitleTag;
import com.webfirmframework.wffweb.tag.html.attribute.Charset;
import com.webfirmframework.wffweb.tag.html.attribute.Href;
import com.webfirmframework.wffweb.tag.html.attribute.Name;
import com.webfirmframework.wffweb.tag.html.attribute.Rel;
import com.webfirmframework.wffweb.tag.html.attribute.Type;
import com.webfirmframework.wffweb.tag.html.attribute.global.ClassAttribute;
import com.webfirmframework.wffweb.tag.html.attribute.global.Id;
import com.webfirmframework.wffweb.tag.html.formatting.Small;
import com.webfirmframework.wffweb.tag.html.html5.attribute.Content;
import com.webfirmframework.wffweb.tag.html.html5.attribute.global.DataAttribute;
import com.webfirmframework.wffweb.tag.html.html5.stylesandsemantics.Footer;
import com.webfirmframework.wffweb.tag.html.html5.stylesandsemantics.Header;
import com.webfirmframework.wffweb.tag.html.links.A;
import com.webfirmframework.wffweb.tag.html.links.Link;
import com.webfirmframework.wffweb.tag.html.lists.Li;
import com.webfirmframework.wffweb.tag.html.lists.Ul;
import com.webfirmframework.wffweb.tag.html.metainfo.Head;
import com.webfirmframework.wffweb.tag.html.metainfo.Meta;
import com.webfirmframework.wffweb.tag.html.stylesandsemantics.Div;
import com.webfirmframework.wffweb.tag.html.stylesandsemantics.Span;
import com.webfirmframework.wffweb.tag.htmlwff.NoTag;

import Model.Avis;
public class HTMLGenerator {
	
	private ArrayList<Avis>  listAvis;
	
	private Html html;
	
	public HTMLGenerator(ArrayList<Avis> listAvis) {
		this.listAvis = listAvis;
	}

	

	public Html getHtml() {
		return html;
	}



	public void setHtml(Html html) {
		this.html = html;
	}



	@SuppressWarnings("serial")
	public Html getReportHTML() {
				
		final Rel rel4 = new Rel("stylesheet");
		final ClassAttribute classAttribute17 = new ClassAttribute("col col-1");
		final ClassAttribute classAttribute18 = new ClassAttribute("col col-2");
		final ClassAttribute classAttribute19 = new ClassAttribute("col col-3");
		final ClassAttribute classAttribute20 = new ClassAttribute("col col-4");
		final ClassAttribute classAttribute21 = new ClassAttribute("table-row");
		final DataAttribute dataAttribute22 = new DataAttribute("label", "ID");
		final DataAttribute dataAttribute23 = new DataAttribute("label", "Titre d'avis");
		final DataAttribute dataAttribute24 = new DataAttribute("label", "Lien");
		final DataAttribute dataAttribute25 = new DataAttribute("label", "Date de publication");
		final ClassAttribute classAttribute11 = new ClassAttribute("major container 75%");
		
		Html html = new Html(null) {{
		    new Head(this) {{
		        new TitleTag(this) {{
		            new NoTag(this, "TSEWATCH VOUS PRESENTE");
		        }};
		        new Meta(this,
		            new Charset("utf-8"));
		        new Meta(this,
		            new Name("viewport"),
		            new Content("width=device-width, initial-scale=1"));
		        new Link(this,
		            rel4,
		            new Href(Const.URL_MAIN_CSS));
		        new Link(this,
		            rel4,
		            new Type("text/css"),
		            new Href(Const.URL_STYLE_CSS));
		    }};
		    new Body(this) {{
		        new Div(this,
		            new Id("header")) {{
		            new Span(this,
		                new ClassAttribute("logo icon fa-paper-plane-o"));
		            new H1(this) {{
		                new NoTag(this, "Rapport");
		            }};
		        }};
		        
		        new Div(this,
		            new Id("main")) {{
		            new Header(this,
		                classAttribute11) {{
		                new H2(this) {{
		                    new NoTag(this, "Powered by TSE students ");
		                    new Br(this);
		                }};
		            }};
		            new H1(this,
		                new Id("guoh1")) {{
		                new NoTag(this, "Look, we found this!");
		            }};
		            new H2(this) {{
		                new Small(this) {{   
		                    new NoTag(this, "Présenter par DIGITAL-LEAGUE");
		                }};
		            }};
		            new Hr(this,
		                new ClassAttribute("brace"));
		            new Br(this);
		            new Br(this);
		            new Br(this);
		            new Div(this,
		                new ClassAttribute("container")) {{
		                new Ul(this,
		                    new ClassAttribute("responsive-table")) 
		                {{
		                    new Li(this,
		                        new ClassAttribute("table-header")) {{
		                        new Div(this,
		                            classAttribute17) {{
		                            new NoTag(this, "\nID ");
		                        }};
		                        new Div(this,
		                            classAttribute18) {{
		                            new NoTag(this, "\nTitre d'avis ");
		                        }};
		                        new Div(this,
		                            classAttribute19) {{
		                            new NoTag(this, "\nLien ");
		                        }};
		                        new Div(this,
		                            classAttribute20) {{
		                            new NoTag(this, "\nDate de publication ");
		                        }};
		                    }};
		                    
		                    for(int i = 0 ; i < listAvis.size() ; i++) {
		                    	 final String strIndex = "\n" + String.valueOf(i+1);
		                    	 final String strTitre = "\n" + listAvis.get(i).getTitre();
		                    	 final String strLink = "" + listAvis.get(i).getLink();
		                    	 final String strDate = "\n" + listAvis.get(i).getDate();
		                    	 new Li(this,
		 		                        classAttribute21) {{
		 		                        new Div(this,
		 		                            classAttribute17,
		 		                            dataAttribute22) {{
		 		                            new NoTag(this, strIndex);
		 		                        }};
		 		                        new Div(this,
		 		                            classAttribute18,
		 		                            dataAttribute23) {{
		 		                            new NoTag(this, strTitre);
		 		                        }};
		 		                        new Div(this,
		 		                            classAttribute19,
		 		                            dataAttribute24) {{
		 		                            new A(this,
		 		                                new Href(strLink)) {{
		 		                                new NoTag(this,"Lien");
		 		                            }};
		 		                        }};
		 		                        new Div(this,
		 		                            classAttribute20,
		 		                            dataAttribute25) {{
		 		                            new NoTag(this, strDate);
		 		                        }};
		 		                    }};
		                    }
		                    
		                    
		                    
		                }};
		            }};
		            new Footer(this,
		                classAttribute11) {{
		                new H3(this) {{
		                    new NoTag(this, "Place to put something we want");
		                }};
		                new P(this) {{
		                    new NoTag(this, "Place to put something we want");
		                }};
		            }};
		        }};
		        new Div(this,
		            new Id("footer")) {{
		            new Div(this,
		                new ClassAttribute("container 75%")) {{
		                new Ul(this,
		                    new ClassAttribute("copyright")) {{
		                    new Li(this) {{
		                        new NoTag(this, "&");
		                        new NoTag(this, "copy;");
		                        new NoTag(this, "Télécom Saint-Etienne All rights reserved.");
		                    }};
		                    new Li(this) {{
		                        new NoTag(this, "Design: ");
		                        new A(this,
		                            new Href("https://telecom-st-etienne.fr")) {{
		                            new NoTag(this, "TSE");
		                        }};
		                        new NoTag(this, " Students");
		                    }};
		                }};
		            }};
		        }};
		    }};
		}};
		html.setPrependDocType(true);
		this.html = html;
		return html;
	}

	
	
	public void generateReport(String path,String fileName) {
		this.html = getReportHTML();
		try {
			html.toOutputStream(new FileOutputStream(path + fileName+".html"),"UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	
	
	
	/**
	 * Test generating html file function
	 * 
	 * For the test,
	 * The html generated will be found just in the folder src/main/resources/ 
	 * TODO:
	 * Put files .css to their server(MUST DO)! So that we can get remote access to these files
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<Avis> list2Test = new ArrayList<Avis>();
		
		// Date,Titre,Lien
		list2Test.add(new Avis("06/06/2019", "Titre d'avis1", "https://www.google.com"));
		list2Test.add(new Avis("05/06/2019", "Titre d'avis2", "https://www.telecom-st-etienne.fr"));
		list2Test.add(new Avis("04/06/2019", "Titre d'avis3", "https://www.youtube.com"));
		
		HTMLGenerator generator = new HTMLGenerator(list2Test);
		generator.generateReport("/Users/gkp/Desktop/", "test1234");		
		System.out.println(generator.getHtml().toBigHtmlString());
	}

}

