import java.util.*;

/**
 * Represents a text segment of a document that needs to be parsed.
 */
abstract class TextSegment {
    private String content;

    TextSegment(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public abstract String accept(DocumentVisitor documentVisitor);
}

/**
 * Uses visitors to parse documents and provide dokuwiki and markdown outputs.
 */
class WikiGenerator {
    private final List<TextSegment> textSegments;

    public WikiGenerator(List<TextSegment> textSegments) {
        this.textSegments = textSegments;
    }

    public StringBuilder getDokuWikiDocument() {
        DokuWikiVisitor dokuWikiVisitor = new DokuWikiVisitor();
        StringBuilder s = new StringBuilder();

        for (TextSegment textSegment : textSegments)
            s.append(textSegment.accept(dokuWikiVisitor));

        return s;
    }

    public StringBuilder getMarkdownDocument() {
        MarkDownSegment markDownSegment = new MarkDownSegment();
        StringBuilder s = new StringBuilder();

        for (TextSegment textSegment : textSegments)
            s.append(textSegment.accept(markDownSegment));

        return s;
    }
}

class ItalicTextSegment extends TextSegment {
    ItalicTextSegment(String content) {
        super(content);
    }

    @Override
    public String accept(DocumentVisitor documentVisitor) {
        return documentVisitor.visit(this);
    }
}

class BoldTextSegment extends TextSegment {
    BoldTextSegment(String content) {
        super(content);
    }

    @Override
    public String accept(DocumentVisitor documentVisitor) {
        return documentVisitor.visit(this);
    }
}

class UrlSegment extends TextSegment {
    private String description;

    public String getDescription() {
        return description;
    }

    public UrlSegment(String url, String description) {
        super(url);
        this.description = description;
    }

    @Override
    public String accept(DocumentVisitor documentVisitor) {
        return documentVisitor.visit(this);
    }
}

class PlainTextSegment extends TextSegment {
    PlainTextSegment(String content) {
        super(content);
    }

    @Override
    public String accept(DocumentVisitor documentVisitor) {
        return documentVisitor.visit(this);
    }
}

interface DocumentVisitor {
    String visit(ItalicTextSegment italicTextSegment);

    String visit(BoldTextSegment boldTextSegment) ;

    String visit(UrlSegment urlSegment);

    String visit(PlainTextSegment plainTextSegment);
}

class DokuWikiVisitor implements DocumentVisitor {
    @Override
    public String visit(ItalicTextSegment italicTextSegment) {
        return "//" + italicTextSegment.getContent() + "//";
    }

    @Override
    public String visit(BoldTextSegment boldTextSegment) {
        return "**" + boldTextSegment.getContent() + "**";
    }

    @Override
    public String visit(UrlSegment urlSegment) {
        return "[[" + urlSegment.getContent() + "|" + urlSegment.getDescription() + "]]";
    }

    @Override
    public String visit(PlainTextSegment plainTextSegment) {
        return plainTextSegment.getContent();
    }
}

class MarkDownSegment implements DocumentVisitor {
    @Override
    public String visit(ItalicTextSegment italicTextSegment) {
        return "*" + italicTextSegment.getContent() + "*";
    }

    @Override
    public String visit(BoldTextSegment boldTextSegment) {
        return "**" + boldTextSegment.getContent() + "**";
    }

    @Override
    public String visit(UrlSegment urlSegment) {
        return "[" + urlSegment.getDescription() + "](" + urlSegment.getContent() + ")";
    }

    @Override
    public String visit(PlainTextSegment plainTextSegment) {
        return plainTextSegment.getContent();
    }
}

public class Main {
    public static void main(String[] args) {

        List<TextSegment> textSegments = getTextSegments();

        WikiGenerator generator = new WikiGenerator(textSegments);

        System.out.println("----------------------Dokuwiki----------------------");
        System.out.println(generator.getDokuWikiDocument());
        System.out.println("---------------------MardownWiki--------------------");
        System.out.println(generator.getMarkdownDocument());

        // See what happens if the TextSegment's accept method is not abstract and the subclasses do not override it
        //  - testing this needs changes in the visitor interface as well
    }

    public static List<TextSegment> getTextSegments() {
        List<TextSegment> textSegments = new ArrayList<>();

        textSegments.add(new PlainTextSegment("Mecanismul din spatele pattern-ului "));
        textSegments.add(new ItalicTextSegment("Visitor"));
        textSegments.add(new PlainTextSegment(" poartă numele de "));
        textSegments.add(new BoldTextSegment("double-dispatch"));
        textSegments.add(new PlainTextSegment(".\n"));
        textSegments.add(new UrlSegment("https://ocw.cs.pub.ro/courses/poo-ca-cd/laboratoare/tutorial-doubledispatch",
                "Tutorialul de double-dispatch"));
        textSegments.add(new PlainTextSegment(" oferă mai multe detalii legate de acest mecanism.\n"));
        textSegments.add(new PlainTextSegment("Pattern-ul "));
        textSegments.add(new BoldTextSegment("Visitor"));
        textSegments.add(new PlainTextSegment(" este util când se doreşte prelucrarea unei "));
        textSegments.add(new ItalicTextSegment("structuri complexe"));
        textSegments.add(new PlainTextSegment(", ce cuprinde mai multe "));
        textSegments.add(new ItalicTextSegment("obiecte de tipuri diferite"));
        textSegments.add(new PlainTextSegment(".\n"));

        return textSegments;
    }
}