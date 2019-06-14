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
import com.webfirmframework.wffweb.tag.html.attribute.Src;
import com.webfirmframework.wffweb.tag.html.attribute.Type;
import com.webfirmframework.wffweb.tag.html.attribute.global.ClassAttribute;
import com.webfirmframework.wffweb.tag.html.attribute.global.Id;
import com.webfirmframework.wffweb.tag.html.attributewff.CustomAttribute;
import com.webfirmframework.wffweb.tag.html.formatting.Small;
import com.webfirmframework.wffweb.tag.html.html5.attribute.Content;
import com.webfirmframework.wffweb.tag.html.html5.attribute.global.DataAttribute;
import com.webfirmframework.wffweb.tag.html.html5.stylesandsemantics.Footer;
import com.webfirmframework.wffweb.tag.html.html5.stylesandsemantics.Header;
import com.webfirmframework.wffweb.tag.html.images.Img;
import com.webfirmframework.wffweb.tag.html.links.A;
import com.webfirmframework.wffweb.tag.html.links.Link;
import com.webfirmframework.wffweb.tag.html.lists.Li;
import com.webfirmframework.wffweb.tag.html.lists.Ul;
import com.webfirmframework.wffweb.tag.html.metainfo.Head;
import com.webfirmframework.wffweb.tag.html.metainfo.Meta;
import com.webfirmframework.wffweb.tag.html.stylesandsemantics.Div;
import com.webfirmframework.wffweb.tag.html.stylesandsemantics.Span;
import com.webfirmframework.wffweb.tag.html.stylesandsemantics.StyleTag;
import com.webfirmframework.wffweb.tag.html.tables.TBody;
import com.webfirmframework.wffweb.tag.html.tables.Table;
import com.webfirmframework.wffweb.tag.html.tables.Td;
import com.webfirmframework.wffweb.tag.html.tables.Th;
import com.webfirmframework.wffweb.tag.html.tables.Tr;
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
		final ClassAttribute classAttribute12 = new ClassAttribute("fifteen");
		final ClassAttribute classAttribute13 = new ClassAttribute("fifty_five");

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
		        new StyleTag(this) {{
		            new NoTag(this, "* {\n            margin: 0;\n            padding: 0;\n            list-style: none;\n            text-decoration: none;\n        }\n        \n        body {\n            font-family: 'lato', sans-serif;\n        }\n        \n        #guoh1 {\n            text-align: center;\n            font-size: 3em;\n            margin-bottom: 10px;\n            text-transform: uppercase;\n            font-weight: bold;\n            color: #333b20;\n            text-shadow: 0 1px 2px rgba(0, 0, 0, 0.15);\n        }\n        \n        h2 {\n            font-size: 26px;\n            margin: 20px 0;\n            text-align: center;\n        }\n        \n        .brace {\n            width: 80%;\n            margin: 0 auto;\n        }\n        \n        .header {\n            width: 100%;\n            height: 80px;\n            display: block;\n            background-color: #fff;\n        }\n        \n        .all_in_box_white {\n            width: 80%;\n            height: 100%;\n            position: absolute;\n            top: 0;\n            bottom: 0;\n            left: 0;\n            right: 0;\n            margin: auto;\n            background-color: white;\n        }\n        \n        .logoHeader img {\n            max-width: 100%;\n            min-width: 100%;\n            height: auto;\n            width: auto;\n        }\n        \n        footer {\n            width: 100%;\n            background-color: #fff;\n            padding: 60px 0px;\n            text-align: center;\n        }\n        \n        .logo_footer img {\n            min-width: 100%;\n            max-width: 100%;\n            height: auto;\n            width: auto;\n        }\n        \n        table {\n            font-family: arial, sans-serif;\n            border-collapse: collapse;\n            width: 100%;\n        }\n        \n        .fifteen {\n            width: 20%;\n        }\n        \n        .fifty_five {\n            width: 40%;\n        }\n        \n        td,\n        th {\n            border: 1px solid #dddddd;\n            text-align: left;\n            padding: 8px;\n            white-space: nowrap;\n            overflow: hidden;\n        }\n        \n        tr:nth-child(even) {\n            background-color: #dddddd;\n        }");
		        }};
		    }};
		    new Body(this,
		        new CustomAttribute("bgcolor", "#D3D3D3")) {{
		        new Div(this,
		            new ClassAttribute("all_in_box_white")) {{
		            new Header(this) {{
		                new Div(this,
		                    new ClassAttribute("header")) {{
		                    new Div(this,
		                        new ClassAttribute("inner_header")) {{
		                        new Div(this,
		                            new ClassAttribute("logoHeader")) {{
		                            new Img(this,
		                                new Src("https://drive.google.com/uc?export=download&id=1kup6UAR8rlJBwp164euB-q75sn370kmG"));
		                        }};
		                    }};
		                }};
		            }};
		            new Br(this);
		            new Br(this);
		            new Br(this);
		            new H2(this) {{
		                new NoTag(this, "Présenté par DIGITAL-LEAGUE");
		                new Br(this);
		            }};
		            new H1(this,
		                new Id("guoh1")) {{
		                new NoTag(this, "Look, we found this!");
		            }};
		            new Hr(this,
		                new ClassAttribute("brace"));
		            new Br(this);
		            new Br(this);
		            new Br(this);
		            new Table(this) {{
		                new TBody(this) {{
		                    new Tr(this) {{
		                        new Th(this,
		                            classAttribute12) {{
		                            new NoTag(this, "ID");
		                        }};
		                        new Th(this,
		                            classAttribute13) {{
		                            new NoTag(this, "Titre");
		                        }};
		                        new Th(this,
		                            classAttribute12) {{
		                            new NoTag(this, " Link");
		                        }};
		                        new Th(this,
		                            classAttribute12) {{
		                            new NoTag(this, "Date");
		                        }};
		                    }};
		                    for(int i = 0 ; i < listAvis.size() ; i++) {
		                    	 final String strIndex = "\n" + String.valueOf(i+1);
		                    	 final String strTitre;
		                    	 if(listAvis.get(i).getTitre().length() > 40)
		                    		 strTitre = "\n" + listAvis.get(i).getTitre().substring(0, 39) + "...";
		                    	 else {
		                    		 strTitre = "\n" + listAvis.get(i).getTitre();
		                    	 }
		                    	 final String strLink = "" + listAvis.get(i).getLink();
		                    	 final String strDate = "\n" + listAvis.get(i).getDate();
		                    	 
		                    	 
		                    	 new Tr(this) {{
				                        new Td(this,
				                            classAttribute12) {{
				                            new NoTag(this, strIndex);
				                        }};
				                        new Td(this,
				                            classAttribute13) {{
				                            new NoTag(this, strTitre);
				                        }};
				                        new Td(this,
				                            classAttribute12) {{
				                            new A(this,
				                                new Href(strLink)) {{
				                                new NoTag(this, "Lien");
				                            }};
				                        }};
				                        new Td(this,
				                            classAttribute12) {{
				                            new NoTag(this, strDate);
				                        }};
				                    }};
		                    }
		                    
		                    
		                }};
		            }};
		            new Footer(this) {{
		                new Div(this,
		                    new ClassAttribute("logo_footer")) {{
		                    new Img(this,
		                        new Src("https://drive.google.com/uc?export=download&id=172om9at3bPfO7GbSwtl52CBVTTiQCJcr"));
		                }};
		                new P(this) {{
		                    new NoTag(this, "contact@digital-league.org");
		                }};
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


	
	public void generateReport(String fileName) {
		this.html = getReportHTML();
		
		try {
			html.toOutputStream(new FileOutputStream(Const.FOLDER_RAPPORT + fileName +".html"),"UTF-8");
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
		generator.generateReport("test1234");		
		System.out.println(generator.getHtml().toBigHtmlString());
	}

}

