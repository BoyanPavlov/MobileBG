package entities.filters;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComplexFilterParser {
    // Split into individual conditions first
    private static final Pattern SPLIT_PATTERN = 
            Pattern.compile("\\s*&&\\s*|\\s*\\|\\|\\s*");
            
    // Then match each condition
    private static final Pattern CONDITION_PATTERN =
            Pattern.compile("^(?:(\\w+)\\s*([<>=]+)\\s*|\\s*)([^\\s]+)\\s*$");

    public static List<FilterGroup> parse(String expression) {
        List<FilterGroup> groups = new ArrayList<>();
        
        // Remove any parentheses and normalize spaces
        expression = expression.replaceAll("[()]", "").trim();
        
        // First split by OR
        String[] orParts = expression.split("\\s*\\|\\|\\s*");
        
        for (String orPart : orParts) {
            List<FilterCondition> conditions = new ArrayList<>();
            
            // Then split by AND
            String[] parts = SPLIT_PATTERN.split(orPart.trim());
            
            for (String part : parts) {
                part = part.trim();
                
                Matcher matcher = CONDITION_PATTERN.matcher(part);
                if (matcher.find()) {
                    String field = matcher.group(1);
                    String operator = matcher.group(2);
                    String value = matcher.group(3);
                    
                    // If no field/operator specified, infer them
                    if (field == null) {
                        if (value.matches("\\d+")) {
                            field = "year";
                            operator = "=";
                        } else {
                            field = "brand";
                            operator = "=";
                        }
                    }
                    
                    // Handle year<2000 format
                    if (value.toLowerCase().startsWith("year") && value.matches("year[<>=]+\\d+")) {
                        field = "year";
                        operator = value.replaceAll("year|\\d+", "");
                        value = value.replaceAll("\\D+", "");
                    }
                    
                    value = value.replaceAll("[\"']", "").trim();
                    conditions.add(new FilterCondition(field, operator, value));
                }
            }
            
            if (!conditions.isEmpty()) {
                groups.add(new FilterGroup(conditions));
            }
        }
        
        return groups;
    }
}
